package pl.sda.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.service.CourseService;
import pl.sda.bootcamp.service.RoleService;
import pl.sda.bootcamp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/kurs")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;
    private final RoleService roleService;

    public CourseController(CourseService courseService,
                            UserService userService,
                            RoleService roleService) {
        this.courseService = courseService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/list";
    }

    @GetMapping("/zapis/{courseId}")
    public String signIn(@PathVariable Long courseId,
                         Model model,
                         final HttpSession session) {
        Course chosenCourse = this.courseService.getCourse(courseId);
        session.setAttribute("courseId", courseId);
        model.addAttribute("chosenCourse", chosenCourse);
        model.addAttribute("user", User.builder().build());
        return "course/signup";
    }

    @PostMapping("/zapis")
    public String signedIn(@Valid @ModelAttribute User user,
                           BindingResult result,
                           final HttpServletRequest httpServletRequest,
                           Model model) {
        Long courseId = (Long) httpServletRequest.getSession().getAttribute("courseId");
        Course chosenCourse = this.courseService.getCourse(courseId);
        if (result.hasErrors()) {
            model.addAttribute("chosenCourse", chosenCourse);
            return "course/signup";
        }

        User userAlreadyExists =
                this.userService.findByEmail(user.getEmail());

        if (Objects.nonNull(userAlreadyExists)) {
            if (!userAlreadyExists.getCourses().contains(chosenCourse)) {
                userAlreadyExists.getCourses().add(chosenCourse);
                this.userService.updateUser(userAlreadyExists);
                model.addAttribute("user", userAlreadyExists);
            } else {
                return "course/alreadysignedup";
            }
        } else {
            user.setCourses(new ArrayList<>());
            user.getCourses().add(chosenCourse);
            user.setRole(this.roleService.findByRoleName("user"));
            user.setHourlyRate(0.0);
            this.userService.saveUser(user);
            model.addAttribute("user", user);
        }
        model.addAttribute("chosenCourse", chosenCourse);
        httpServletRequest.getSession().invalidate();
        return "course/ordersummary";
    }
}
