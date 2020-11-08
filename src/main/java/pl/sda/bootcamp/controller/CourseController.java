package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.RoleService;
import pl.sda.bootcamp.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/kurs")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;
    private final RoleService roleService;

    private User newStudent;

    public CourseController(CourseService courseService,
                            UserService userService,
                            RoleService roleService) {
        this.courseService = courseService;
        this.userService = userService;
        this.roleService = roleService;
        this.newStudent = null;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/list";
    }

    @GetMapping("/zapis/{courseId}")
    public String signIn(@PathVariable Long courseId, Model model) {
        this.newStudent = User.builder().build();
        Course chosenCourse = this.courseService.getCourse(courseId);
        this.newStudent.setCourses(new ArrayList<>());
        this.newStudent.getCourses().add(chosenCourse);
        model.addAttribute("chosenCourse", chosenCourse);
        model.addAttribute("newStudent", newStudent);
        return "course/signup";
    }

    @PostMapping("/zapis")
    public String signedIn(@ModelAttribute User newStudent,
                           Model model) {
        this.newStudent.setFirstName(newStudent.getFirstName());
        this.newStudent.setLastName(newStudent.getLastName());
        this.newStudent.setEmail(newStudent.getEmail());
        this.newStudent.setPhone(newStudent.getPhone());
        this.newStudent.setRole(this.roleService.findByRoleName("user"));
        this.newStudent.setHourlyRate(0.0);
        this.userService.addUser(this.newStudent);
        model.addAttribute("savedStudent", this.newStudent);
        return "course/ordersummary";
    }
}
