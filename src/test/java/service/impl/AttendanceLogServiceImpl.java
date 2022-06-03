package service.impl;

import com.test.app.entity.AttendanceLog;
import com.test.app.exception.NotFoundException;
import com.test.app.repo.AttendanceLogRepo;
import service.AttendanceLogService;
import service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceLogServiceImpl implements AttendanceLogService {

    private final AttendanceLogRepo attendanceLogRepo;
    private final StudentService studentService;

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
        if (attendanceLogRepo.existsById(id))
            throw new NotFoundException("Лог посещаемости студента не найден");
        attendanceLogRepo.deleteById(id);
    }
}
