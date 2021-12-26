package com.jab.microservices.templates;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = {"/template2"})
    public String template2(
            @RequestParam(value = "param", required = true) Integer parameter,
            Model model) {
        model.addAttribute("parameter", parameter);
        return "template2";
    }

    @GetMapping("/form1")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "form1";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

}
