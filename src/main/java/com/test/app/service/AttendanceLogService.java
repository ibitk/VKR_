package com.test.app.service;

import com.test.app.entity.AttendanceLog;
import com.test.app.model.CanteenManagerAttendanceReport;
import com.test.app.model.CanteenManagerReport;

import java.time.ZonedDateTime;
import java.util.List;

public interface AttendanceLogService {
    /**
     * метод поиска лога посещений конкретного студента
     *
     * @param studentId
     * @return
     */
    AttendanceLog findAttendanceLogByStudentId(long studentId);

    /**
     * изменение лога посещений
     *
     * @param attendanceLog объект лог посещений
     * @return объект типа лог посещений
     */
    AttendanceLog addAttendance(long studentId, ZonedDateTime visitedAt);

    /**
     * удаление лога посещения
     *
     * @param id айли лога
     */
    void removeAttendanceLogById(long id);

    List<CanteenManagerReport> getAttendanceCurrentReport();

    List<CanteenManagerReport> getAttendanceCurrentRepoByBenefitType(int benefitType);

    List<CanteenManagerAttendanceReport> getGroupedAttendanceReport();

    List<Integer> getStudentVisitedDaysByCurrentMonth(long studentId);

    void changeAttendance(long studentId, boolean isVisited);


}
