package com.jab.microservices.templates;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "My View");
        return "index";
    }

    @GetMapping(value = {"/template1"})
    public String template1(Model model) {
        model.addAttribute("title", "My View");
        return "template1";
    }

}
