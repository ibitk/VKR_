package com.test.app.repo;

import com.test.app.entity.AttendanceLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceLogRepo extends CrudRepository<AttendanceLog, Long> {
}
