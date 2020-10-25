package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/kurs")
public class CourseController {
    private List<String> cities;
    private List<Course> courses;

    public CourseController() {
        this.courses = List.of(
                Course.builder().id(1)
                      .name("Java").city("Warszawa").mode("dzienny").build(),
                Course.builder().id(2)
                      .name("C++").city("Kraków").mode("weekendowy").build(),
                Course.builder().id(3)
                      .name("Spring").city("Szczecin").mode("dzienny").build(),
                Course.builder().id(4)
                      .name("Python").city("Warszawa").mode("weekendowy").build()
        );
        this.cities = this.courses.stream()
                                  .map(Course::getCity)
                                  .distinct()
                                  .sorted()
                                  .collect(Collectors.toList());
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

    @GetMapping("/zapis")
    public String signIn(Model model) {
        model.addAttribute("newStudent", Student.builder().build());
        model.addAttribute("courses", courses);
        return "course/sign-in";
    }

    @PostMapping("/zapis")
    public String signedIn(@ModelAttribute Student newStudent,
                           @RequestParam Integer courseId) {
        System.out.println(courseId);
        newStudent.setCourse(this.courses.stream().filter(
                course -> course.getId().equals(courseId)).findAny().orElse(null));
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
