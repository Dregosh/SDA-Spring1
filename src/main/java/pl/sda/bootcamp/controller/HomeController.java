package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:kurs/lista";
    }

    @GetMapping("/panel-klienta")
    public String showStudentPanel() {
        return "panels/studentpanel";
    }

    @GetMapping("/panel-trenera")
    public String showTeacherPanel() {
        return "panels/teacherpanel";
    }
}
