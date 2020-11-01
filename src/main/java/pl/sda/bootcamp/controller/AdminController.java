package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;
import pl.sda.bootcamp.model.Teacher;
import pl.sda.bootcamp.service.CityService;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.StudentService;
import pl.sda.bootcamp.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final int UNREMOVABLE_THRESHOLD = 3;

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final CityService cityService;

    private Course newCourse;
    private Teacher newTeacher;

    public AdminController(CourseService courseService,
                           TeacherService teacherService,
                           StudentService studentService,
                           CityService cityService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
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

    @GetMapping("/listastudentow")
    public String adminStudentList(Model model) {
        model.addAttribute("students", this.studentService.getStudents());
        return "admin/studentlist";
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
        this.newCourse.setTeacher(this.teacherService.getTeacher(selectedTeacherId));
        this.courseService.addCourse(this.newCourse);
        System.out.println("Added new Course to DB: " + this.newCourse);
        this.newCourse = null;
        return "redirect:/admin/listakursow";
    }

    @GetMapping("/usunkurs/{courseId}")
    public String removeCourse(@PathVariable Long courseId) {
        if (courseId > UNREMOVABLE_THRESHOLD) {
            Course removedCourse = this.courseService.removeCourse(courseId);
            System.out.println("Removed Course from DB: " + removedCourse);
        }
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
        this.teacherService.addTeacher(this.newTeacher);
        System.out.println("Added new Teacher to DB: " + this.newTeacher);
        this.newTeacher = null;
        return "redirect:/admin/listatrenerow";
    }

    @GetMapping("/usuntrenera/{teacherId}")
    public String removeTeacher(@PathVariable Long teacherId) {
        if (teacherId > UNREMOVABLE_THRESHOLD) {
            Teacher removedTeacher = this.teacherService.removeTeacher(teacherId);
            System.out.println("Removed Teacher from DB: " + removedTeacher);
        }
        return "redirect:/admin/listatrenerow";
    }

    @GetMapping("/usunstudenta/{studentId}")
    public String removeStudent(@PathVariable Long studentId) {
        if (studentId > UNREMOVABLE_THRESHOLD) {
            Student removedStudent = this.studentService.removeStudent(studentId);
            System.out.println("Removed Student from DB: " + removedStudent);
        }
        return "redirect:/admin/listastudentow";
    }
}
