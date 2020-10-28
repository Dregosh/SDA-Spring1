package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/kurs")
public class CourseController {
    private List<String> cities;
    private List<Course> courses;
    private Student newStudent;

    public CourseController() {
        this.courses = List.of(
                Course.builder().id(1)
                      .name("Java Developer")
                      .city("Warszawa")
                      .beginDate(LocalDate.of(2020, 11, 14))
                      .endDate(LocalDate.of(2021, 7, 18))
                      .mode("weekendowy")
                      .build(),
                Course.builder().id(2)
                      .name("C++ od podstaw")
                      .city("Kraków")
                      .beginDate(LocalDate.of(2020, 12, 7))
                      .endDate(LocalDate.of(2021, 8, 18))
                      .mode("weekendowy")
                      .build(),
                Course.builder().id(3)
                      .name("Frontend Developer")
                      .city("Szczecin")
                      .beginDate(LocalDate.of(2020, 12, 14))
                      .endDate(LocalDate.of(2021, 3, 15))
                      .mode("dzienny")
                      .build(),
                Course.builder().id(4)
                      .name("Python - analiza danych")
                      .city("Warszawa")
                      .beginDate(LocalDate.of(2021, 1, 14))
                      .endDate(LocalDate.of(2021, 5, 25))
                      .mode("dzienny")
                      .build()
        );
        this.cities = this.courses.stream()
                                  .map(Course::getCity)
                                  .distinct()
                                  .sorted()
                                  .collect(Collectors.toList());
        this.newStudent = null;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("/dodaj")
    public String add(Model model) {
        model.addAttribute("cities", cities);
        model.addAttribute("course", Course.builder().build());
        return "course/add";
    }

    @PostMapping("/dodaj")
    public String create(@ModelAttribute Course course,
                         Model model) {
        model.addAttribute("cities", cities);
        model.addAttribute("course", Course.builder().build());
        model.addAttribute("createdCourse", course);
        return "course/add";
    }

    @GetMapping("/zapis/{courseId}")
    public String signIn(@PathVariable int courseId, Model model) {
        this.newStudent = Student.builder().build();
        this.newStudent.setCourse(this.courses.stream().filter(
                course -> course.getId().equals(courseId)).findAny().orElse(null));
        model.addAttribute("newStudent", newStudent);
        return "course/signup";
    }

    @PostMapping("/zapis")
    public String signedIn(@ModelAttribute Student newStudent,
                           @RequestParam Integer courseId) {
        System.out.println(courseId);
        System.out.println(newStudent);
        return "redirect:/kurs/lista";
    }

    /*@GetMapping("/lista")
    public String list2(@RequestParam(required = false) String idKursu,
                        Model model) {
        model.addAttribute("id", idKursu);
        return "course/list";
    }*/

    /*@GetMapping("/lista")
    public String list(@RequestParam(required = false) String idKursu) {
        System.out.println("Kurs o id: " + idKursu);
        return "course/list";
    }

    @GetMapping("/lista/{idKursu}")
    public String list2(@PathVariable int idKursu) {
        System.out.println("Kurs o id: " + idKursu);
        return "course/list";
    }*/

}
