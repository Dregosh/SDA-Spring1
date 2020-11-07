package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.*;
import pl.sda.bootcamp.service.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CourseService courseService;
    private final CityService cityService;
    private final RoleService roleService;

    public AdminController(UserService userService,
                           CourseService courseService,
                           CityService cityService,
                           RoleService roleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.cityService = cityService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPanel() {
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/listakursow")
    public String adminCourseList(Model model) {
        model.addAttribute("courses", this.courseService.getAllCourses());
        return "admin/courselist";
    }

    @GetMapping("/wszyscyuzytkownicy")
    public String allUsersList(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "admin/alluserlist";
    }

    @GetMapping("/listatrenerow")
    public String adminTeacherList(Model model) {
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/teacherlist";
    }

    @GetMapping("/listastudentow")
    public String adminStudentList(Model model) {
        model.addAttribute("students", this.userService.getAllStudents());
        return "admin/studentlist";
    }

    @GetMapping("/dodajkurs")
    public String addCourseForm(Model model) {
        model.addAttribute("newCourse", Course.builder().build());
        model.addAttribute("modes", Mode.values());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/addcourse";
    }

    @PostMapping("/dodajkurs")
    public String addCourseToDB(@ModelAttribute Course course) {
        this.courseService.addCourse(course);
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/usunkurs/{courseId}")
    public String removeCourse(@PathVariable Long courseId) {
        // TODO
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/dodajtrenera")
    public String addTeacherForm(Model model) {
        model.addAttribute("newTeacher", User.builder().build());
        return "admin/addteacher";
    }

    @PostMapping("/dodajtrenera")
    public String addTeacherToDB(@ModelAttribute User newTeacher) {
        newTeacher.setCourses(new ArrayList<>());
        newTeacher.setRole(this.roleService.findByRoleName("teacher"));
        this.userService.addUser(newTeacher);
        return "redirect:/admin/listatrenerow";
    }

    @GetMapping("/usuntrenera/{teacherId}")
    public String removeTeacher(@PathVariable Long teacherId) {
        // TODO
        return "redirect:/admin/listatrenerow";
    }

    @GetMapping("/dodajstudenta")
    public String addStudent(Model model) {
        model.addAttribute("newStudent", User.builder().build());
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        return "admin/addstudent";
    }

    @PostMapping("/dodajstudenta")
    public String processNewStudent(@ModelAttribute("newStudent") User user) {
        user.setRole(this.roleService.findByRoleName("user"));
        user.setHourlyRate(0.0);
        this.userService.addUser(user);
        return "redirect:/admin/listastudentow";
    }

    @GetMapping("/usunstudenta/{studentId}")
    public String removeStudent(@PathVariable Long studentId) {
        // TODO
        return "redirect:/admin/listastudentow";
    }
}
