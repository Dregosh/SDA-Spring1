package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/kurs")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;

    private Student newStudent;

    public CourseController(CourseService courseService,
                            StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.newStudent = null;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "course/list";
    }

    @GetMapping("/zapis/{courseId}")
    public String signIn(@PathVariable Long courseId, Model model) {
        this.newStudent = Student.builder().build();
        List<Course> courses = this.courseService.getCourses();
        Course chosenCourse =
                courses.stream()
                       .filter(course -> course.getId().equals(courseId))
                       .findAny()
                       .orElse(null);
        this.newStudent.setCourse(chosenCourse);
        model.addAttribute("newStudent", newStudent);
        return "course/signup";
    }

    @PostMapping("/zapis")
    public String signedIn(@ModelAttribute Student newStudent) {
        this.newStudent.setId(this.studentService.getStudents().size() + 1L);
        this.newStudent.setFirstName(newStudent.getFirstName());
        this.newStudent.setLastName(newStudent.getLastName());
        this.newStudent.setEmail(newStudent.getEmail());
        this.newStudent.setPhone(newStudent.getPhone());
        this.studentService.getStudents().add(this.newStudent);
        System.out.println("Zapisał się nowy student: " + this.newStudent);
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
