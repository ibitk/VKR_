package com.test.app.repo;

import com.test.app.entity.AttendanceLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AttendanceLogRepo extends CrudRepository<AttendanceLog, Long> {

    Collection<AttendanceLog> findAttendanceLogsByVisitedAtBetween(final ZonedDateTime visitedAt, final ZonedDateTime visitedAt2);

    Collection<AttendanceLog> findAttendanceLogsByStudentIdAndVisitedAtBetween(long student_id, ZonedDateTime visitedAt, ZonedDateTime visitedAt2);

    Optional<AttendanceLog> getAttendanceLogByStudentIdAndVisitedAtBetween(long student_id, ZonedDateTime visitedAt, ZonedDateTime visitedAt2);

}
