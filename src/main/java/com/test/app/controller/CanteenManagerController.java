package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CanteenManagerController {

    @GetMapping("/CanteenManager")
    public String CanteenManager(Model model) {
        model.addAttribute("title", "Страница заведующего столовой");
        return "CanteenManager";
    }

}