package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Teacher;
import pl.sda.bootcamp.service.CityService;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final CityService cityService;
    private Course newCourse;
    private Teacher newTeacher;

    public AdminController(CourseService courseService,
                           TeacherService teacherService,
                           CityService cityService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.cityService = cityService;
        this.newCourse = null;
        this.newTeacher = null;
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
    public String addCourseForm(Model model) {
        model.addAttribute("newCourse", Course.builder().build());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("teachers", teacherService.getTeachers());
        return "admin/addcourse";
    }

    @PostMapping("/dodajkurs")
    public String addCourseToDB(@ModelAttribute Course course,
                                @RequestParam Long selectedTeacherId) {
        this.newCourse = course;
        List<Course> courses = this.courseService.getCourses();
        this.newCourse.setId(courses.size() + 1L);
        this.newCourse.setTeacher(this.teacherService.getTeacherById(selectedTeacherId));
        courses.add(this.newCourse);
        System.out.println("Added new Course to DB: " + this.newCourse);
        this.newCourse = null;
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/dodajtrenera")
    public String addTeacherForm(Model model) {
        model.addAttribute("newTeacher", Teacher.builder().build());
        return "admin/addteacher";
    }

    @PostMapping("/dodajtrenera")
    public String addTeacherToDB(@ModelAttribute Teacher teacher) {
        this.newTeacher = teacher;
        List<Teacher> teachers = this.teacherService.getTeachers();
        this.newTeacher.setId(teachers.size() + 1L);
        teachers.add(this.newTeacher);
        System.out.println("Added new Teacher to DB: " + this.newTeacher);
        this.newTeacher = null;
        return "redirect:/admin/listatrenerow";
    }
}
