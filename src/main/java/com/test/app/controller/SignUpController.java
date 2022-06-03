package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignUpController {

    @GetMapping("/SignUp")
    public String SignUp(Model model) {
        model.addAttribute("title", "Страница входа");
        return "SignUp";
    }

}