package com.test.app.repo;

import com.test.app.entity.AttendanceLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public interface AttendanceLogRepo extends CrudRepository<AttendanceLog, Long> {

    Collection<AttendanceLog> findAttendanceLogsByVisitedAtBetween(final ZonedDateTime visitedAt, final ZonedDateTime visitedAt2);

}
