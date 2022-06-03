package com.test.app.service;

import com.test.app.entity.AttendanceLog;

import java.time.ZonedDateTime;

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


}
