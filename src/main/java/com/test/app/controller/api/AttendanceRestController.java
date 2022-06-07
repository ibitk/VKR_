package com.test.app.controller.api;

import com.test.app.service.AttendanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/attendance")
@RequiredArgsConstructor
public class AttendanceRestController {

    private final AttendanceLogService attendanceLogService;

    @PutMapping("{studentId}/{isVisited}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<String> changeAttendanceByStudent(@PathVariable boolean isVisited,
                                                            @PathVariable long studentId) {
        attendanceLogService.changeAttendance(studentId, isVisited);
        return ResponseEntity.ok("ok");
    }


}
