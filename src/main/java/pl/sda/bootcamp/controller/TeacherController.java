package pl.sda.bootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/panel-trenera")
@AllArgsConstructor
public class TeacherController {

    private final UserService userService;
    private final CourseService courseService;

    @GetMapping
    public String toCoursesRedirect() {
        return "redirect:/panel-trenera/kursy";
    }

    @GetMapping("/kursy")
    public String showCourses(Model model,
                              Principal principal) {
        User user = this.userService.findByEmail(principal.getName());
        model.addAttribute("courses", user.getTeacherForCourses());
        return "panels/teachercourses";
    }

    @GetMapping("/edycja-konta")
    public String accountEditForm(Model model,
                                  Principal principal) {
        User user = this.userService.findByEmail(principal.getName());
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        model.addAttribute("user", user);
        return "panels/editteacher";
    }

    @PostMapping("/edycja-konta")
    public String saveEditedUser(@Validated(User.ValidationEdited.class)
                                 @ModelAttribute User user,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "panels/editteacher";
        }
        this.userService.updateUser(user);
        return "redirect:/panel-trenera/kursy";
    }
}
