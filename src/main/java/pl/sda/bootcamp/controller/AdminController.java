package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public AdminController(CourseService courseService,
                           TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String adminPanel() {
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/listakursow")
    public String adminCourseList(Model model) {
        model.addAttribute("courses", this.courseService.getCourses());
        return "admin/courselist";
    }

    @GetMapping("/listatrenerow")
    public String adminTeacherList(Model model) {
        model.addAttribute("teachers", this.teacherService.getTeachers());
        return "admin/teacherlist";
    }

    @GetMapping("/dodajkurs")
    public String addCourseForm() {
        return "admin/addcourse";
    }

    @PostMapping("/dodajkurs")
    public String create(@ModelAttribute Course course) {
        List<Course> courses = this.courseService.getCourses();
        course.setId(courses.size() + 1L);
        courses.add(course);
        return "redirect:/admin/listakursow";
    }
}
