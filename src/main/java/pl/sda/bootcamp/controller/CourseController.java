package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/kurs")
public class CourseController {

    @GetMapping("/lista")
    public String list(@RequestParam(required = false) String idKursu) {
        System.out.println("Kurs o id: " + idKursu);
        return "course/list";
    }

}
