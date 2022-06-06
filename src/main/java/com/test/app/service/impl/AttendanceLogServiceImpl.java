package com.test.app.service.impl;

import com.test.app.entity.AttendanceLog;
import com.test.app.exception.NotFoundException;
import com.test.app.model.CanteenManagerAttendanceReport;
import com.test.app.model.CanteenManagerReport;
import com.test.app.repo.AttendanceLogRepo;
import com.test.app.service.AttendanceLogService;
import com.test.app.service.BenefitService;
import com.test.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceLogServiceImpl implements AttendanceLogService {

    private final AttendanceLogRepo attendanceLogRepo;
    private final StudentService studentService;
    private final BenefitService benefitService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public AttendanceLog findAttendanceLogByStudentId(long studentId) {
        return null;
    }

    @Override
    public AttendanceLog addAttendance(long studentId, ZonedDateTime time) {

        var student = studentService.getStudentById(studentId);

        var attendanceLog = AttendanceLog.builder()
                .student(student)
                .visitedAt(time)
                .build();
        return attendanceLogRepo.save(attendanceLog);
    }

    @Override
    public void removeAttendanceLogById(long id) {
        if (attendanceLogRepo.existsById(id)) {
            throw new NotFoundException("Лог посещаемости студента не найден");
        }
        attendanceLogRepo.deleteById(id);
    }

    @Override
    public List<CanteenManagerReport> getAttendanceCurrentReport() {
        var localDate = LocalDateTime.now();
        var targetYear = localDate.getMonthValue() >= 9 ? localDate.getYear() : localDate.getYear() - 1;

        var startDate = ZonedDateTime.of(
                LocalDateTime.of(targetYear, 9, 1, 0, 0),
                ZoneId.systemDefault());
        var endDate = ZonedDateTime.now();

        var attendanceLogEntries =
                attendanceLogRepo.findAttendanceLogsByVisitedAtBetween(startDate, endDate);

        //Мапа соответствия идентификатора студента к потраченной сумме
        var counterMap = new HashMap<Long, Double>();

        attendanceLogEntries.forEach((el) -> {
            if (!counterMap.containsKey(el.getStudent().getId())) {
                counterMap.put(el.getStudent().getId(), el.getAmount());
            } else {
                counterMap.merge(el.getStudent().getId(), el.getAmount(), Double::sum);
            }
        });

        var result = counterMap.entrySet().stream()
                .map((el) -> {
                    return CanteenManagerReport.builder()
                            .studentId(el.getKey())
                            .totalCharge(el.getValue())
                            .build();
                })
                .peek((el) -> {
                    var student = studentService.getStudentById(el.getStudentId());
                    el.setStudentName(student.getName());
                })
                .peek((el) -> {
                    var currentBenefit = benefitService.getCurrentBenefit(el.getStudentId());
                    el.setCurrentBenefit(currentBenefit.getAmount());
                    el.setPeriodStart(startDate.format(formatter));
                    el.setPeriodEnd(endDate.format(formatter));
                    el.setBenefitType(currentBenefit.getBenefit().getId());
                })
                .toList();
        return result;

    }

    @Override
    public List<CanteenManagerReport> getAttendanceCurrentRepoByBenefitType(final int benefitType) {
        return getAttendanceCurrentReport().stream()
                .filter((el) -> el.getBenefitType() == benefitType).toList();

    }

    @Override
    public List<CanteenManagerAttendanceReport> getGroupedAttendanceReport() {
        var localDate = LocalDateTime.now();
        var startDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0),
                ZoneId.systemDefault());
        var endDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), localDate.getDayOfMonth() + 1, 0, 0),
                ZoneId.systemDefault());

        var attendanceLog = attendanceLogRepo.findAttendanceLogsByVisitedAtBetween(startDate, endDate);

        var map = new HashMap<Long, CanteenManagerAttendanceReport>();

        attendanceLog.forEach((el) -> {
            var clazz = el.getStudent().getStudentClass();
            var benefitType = benefitService.getCurrentBenefit(el.getStudent().getId())
                    .getBenefit()
                    .getId();
            if (!map.containsKey(clazz.getId())) {
                map.put(clazz.getId(), CanteenManagerAttendanceReport.builder()
                        .groupName(clazz.getName())
                        .build());
                detectPidoras(map.get(clazz.getId()), benefitType);
            } else {
                detectPidoras(map.get(clazz.getId()), benefitType);
            }
        });

        return map.values().stream().toList();

    }

    @Override
    public List<Integer> getStudentVisitedDaysByCurrentMonth(long studentId) {
        var localDate = LocalDateTime.now();
        var startDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), 1, 0, 0),
                ZoneId.systemDefault());
        var endDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), localDate.getMonth().maxLength(), 23, 59),
                ZoneId.systemDefault());

        return attendanceLogRepo.findAttendanceLogsByStudentIdAndVisitedAtBetween(studentId, startDate, endDate).stream()
                .map(el -> el.getVisitedAt().getDayOfMonth())
                .toList();

    }

    @Override
    public void changeAttendance(long studentId, boolean isVisited) {
        var localDate = LocalDateTime.now();
        var startDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0),
                ZoneId.systemDefault());
        var endDate = ZonedDateTime.of(LocalDateTime.of(
                        localDate.getYear(),
                        localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59),
                ZoneId.systemDefault());

        var currentAttendance = attendanceLogRepo.getAttendanceLogByStudentIdAndVisitedAtBetween(studentId, startDate, endDate);
        if (isVisited) {
            if (currentAttendance.isPresent())
                return;

            var student = studentService.getStudentById(studentId);
            var currentBenefit = benefitService.getCurrentBenefit(studentId);
            var attLogToSave = AttendanceLog.builder()
                    .visitedAt(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()))
                    .student(student)
                    .amount(currentBenefit.getAmount())
                    .build();
            attendanceLogRepo.save(attLogToSave);
        } else {
            if (currentAttendance.isEmpty())
                return;
            attendanceLogRepo.delete(currentAttendance.get());
        }
    }

    private void detectPidoras(CanteenManagerAttendanceReport rep, int benefitType) {
        if (benefitType == 1) {
            rep.incrementRichBoys();
        }
        if (benefitType == 2) {
            rep.incrementMaloimuchi();
        }
        if (benefitType == 3) {
            rep.incrementSirot();
        }
        rep.incrementTotal();
    }


}
