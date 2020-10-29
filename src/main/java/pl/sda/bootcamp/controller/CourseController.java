package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/kurs")
public class CourseController {
    private List<String> cities;
    private List<String> teachers;
    private List<Course> courses;
    private Student newStudent;

    public CourseController() {
        initializeCitiesList();
        initializeTeachersList();
        initializeCoursesList();
        this.newStudent = null;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @PostMapping("/dodaj")
    public String create(@ModelAttribute Course course) {
        course.setId(this.courses.size() + 1);
        this.courses.add(course);
        return "redirect:/kurs/adminpanel";
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
    public String signedIn(@ModelAttribute Student newStudent) {
        System.out.println(newStudent);
        this.newStudent.setFirstName(newStudent.getFirstName());
        this.newStudent.setLastName(newStudent.getLastName());
        this.newStudent.setEmail(newStudent.getEmail());
        this.newStudent.setPhone(newStudent.getPhone());
        System.out.println(this.newStudent);
        return "redirect:/kurs/lista";
    }

    @GetMapping("/adminpanel")
    public String adminPanel(Model model) {
        model.addAttribute("courses", courses);
        model.addAttribute("cities", cities);
        model.addAttribute("teachers", teachers);
        model.addAttribute("newCourse", Course.builder().build());
        return "course/adminpanel";
    }

    private void initializeCitiesList() {
        this.cities = new ArrayList<>();
        this.cities.add("Warszawa");
        this.cities.add("Gdańsk");
        this.cities.add("Wrocław");
        this.cities.add("Szczecin");
        this.cities.sort(Comparator.naturalOrder());
    }

    private void initializeTeachersList() {
        this.teachers = new ArrayList<>();
        this.teachers.add("Nowak Jan");
        this.teachers.add("Kowalski Adam");
        this.teachers.add("Kłos Kamil");
        this.teachers.sort(Comparator.naturalOrder());
    }

    private void initializeCoursesList() {
        this.courses = new ArrayList<>();
        this.courses.add(Course.builder().id(1)
                               .name("Java Developer")
                               .city(this.cities.get(0))
                               .beginDate(LocalDate.of(2020, 11, 14))
                               .endDate(LocalDate.of(2021, 7, 18))
                               .mode("weekendowy")
                               .price(7999)
                               .teacher(this.teachers.get(0))
                               .build());
        this.courses.add(Course.builder().id(2)
                               .name("C++ od podstaw")
                               .city(this.cities.get(1))
                               .beginDate(LocalDate.of(2020, 12, 7))
                               .endDate(LocalDate.of(2021, 8, 18))
                               .mode("weekendowy")
                               .price(6999)
                               .teacher(this.teachers.get(1))
                               .build());
        this.courses.add(Course.builder().id(3)
                               .name("Angular Developer")
                               .city(this.cities.get(2))
                               .beginDate(LocalDate.of(2020, 12, 14))
                               .endDate(LocalDate.of(2021, 3, 15))
                               .mode("dzienny")
                               .price(4999)
                               .teacher(this.teachers.get(0))
                               .build());
        this.courses.add(Course.builder().id(4)
                               .name("Tester manualny")
                               .city(this.cities.get(0))
                               .beginDate(LocalDate.of(2021, 1, 14))
                               .endDate(LocalDate.of(2021, 5, 25))
                               .mode("dzienny")
                               .price(4499)
                               .teacher(this.teachers.get(2))
                               .build());
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
