package com.test.app.controller;

import com.test.app.exception.NotFoundException;
import com.test.app.repo.BenefitTypeRepo;
import com.test.app.service.AttendanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("canteen")
@RequiredArgsConstructor
public class CanteenManagerController {

    private final AttendanceLogService attendanceLogService;
    private final BenefitTypeRepo benefitTypeRepo;

    @GetMapping("manager/{categoryId}")

    public String canteenManagerReport(Model model, @PathVariable int categoryId) {

        var title = benefitTypeRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Данный тип льгот не найден"))
                .getName();

        var report = attendanceLogService.getAttendanceCurrentRepoByBenefitType(categoryId);

        model.addAttribute("title", title + ": отчёт");
        model.addAttribute("report", report);

        return "CanteenManagerReportOne";
    }

    @GetMapping("manager")
    public String canteenManagerReportAll(Model model) {

        var report = attendanceLogService.getGroupedAttendanceReport();

        model.addAttribute("title", "Отчет по посещаемости");
        model.addAttribute("report", report);

        return "CanteenManager";
    }

}