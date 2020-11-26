package pl.sda.bootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Role;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/kurs")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/list";
    }

    @GetMapping("/zapis/{courseId}")
    public String signIn(@PathVariable Long courseId,
                         Model model) {
        model.addAttribute("chosenCourse", this.courseService.getCourse(courseId));
        model.addAttribute("user", User.builder()
                                       .role(Role.ROLE_USER)
                                       .hourlyRate(0.0)
                                       .build());
        return "course/signup";
    }

    @PostMapping("/zapis")
    public String signedIn(@Validated(User.ValidationNew.class)
                           @ModelAttribute User user,
                           BindingResult result,
                           @RequestParam String passwordRepeat,
                           @RequestParam Long courseId,
                           Model model) {
        Course chosenCourse = this.courseService.getCourse(courseId);
        if (!user.getEmail().isBlank() &&
            Objects.nonNull(this.userService.findByEmail(user.getEmail()))) {
            result.rejectValue("email", "email_already_exists_err",
                               "Konto z takim adresem e-mail już istnieje w bazie. " +
                               "Zaloguj się i dokonaj zapisu w Panelu Studenta.");
        }
        if (!user.getPassword().equals(passwordRepeat)) {
            result.rejectValue("password", "passwords_match_err",
                               "Wpisane hasła różnią się");
        }
        if (result.hasErrors()) {
            model.addAttribute("chosenCourse", chosenCourse);
            return "course/signup";
        }
        user.setCourses(new ArrayList<>());
        user.getCourses().add(chosenCourse);
        this.userService.saveUser(user);
        model.addAttribute("user", user);
        model.addAttribute("chosenCourse",chosenCourse);
        return"course/ordersummary";
}
}
