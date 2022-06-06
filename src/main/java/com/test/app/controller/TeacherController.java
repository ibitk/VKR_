package com.test.app.controller;

import com.test.app.model.StudentAttendanceModel;
import com.test.app.service.AttendanceLogService;
import com.test.app.service.StudentClassService;
import com.test.app.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final StudentClassService studentClassService;
    private final AttendanceLogService attendanceLogService;

    @GetMapping("attendanceLog")
    public String teacher(Model model) {

        YearMonth yearMonthObject = YearMonth.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth());
        int daysInMonth = yearMonthObject.lengthOfMonth();

        var days = IntStream.range(1, daysInMonth + 1).boxed().toList();
        var student = studentClassService.getStudentsByCuratorId(1);

        var studentsWithAttendance = student.stream()
                .map(StudentAttendanceModel::fromStudent)
                .peek(el -> {
                    var studentVisitedDays = attendanceLogService.getStudentVisitedDaysByCurrentMonth(el.getStudentId());
                    el.setVisitedDays(studentVisitedDays);
                }).toList();


        model.addAttribute("title", "Страница преподавателя");
        model.addAttribute("student", studentsWithAttendance);
        model.addAttribute("dayz", days);
        model.addAttribute("currentDay", LocalDateTime.now().getDayOfMonth());

        return "Teacher";
    }

}