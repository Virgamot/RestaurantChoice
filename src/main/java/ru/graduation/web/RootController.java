package ru.graduation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:root";
    }

    @GetMapping("/root")
    public String rootTest() {
        return "root";
    }
}
