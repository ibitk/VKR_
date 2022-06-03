package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HeadTeacherController {

    @GetMapping("/HeadTeacher")
    public String HeadTeacher(Model model) {
        model.addAttribute("title", "Страница старшего преподавателя");
        return "HeadTeacher";
    }

}