package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TeacherController {

    @GetMapping("/Teacher")
    public String Teacher(Model model) {
        model.addAttribute("title", "Страница преподавателя");
        return "Teacher";
    }

}