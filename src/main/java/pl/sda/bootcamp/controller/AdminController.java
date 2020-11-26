package pl.sda.bootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.*;
import pl.sda.bootcamp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CourseService courseService;
    private final CityService cityService;

    @GetMapping
    public String adminPanel() {
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses")
    public String adminCourseList(Model model) {
        model.addAttribute("courses", this.courseService.getAllCourses());
        return "admin/courselist";
    }

    @GetMapping("/users")
    public String allUsersList(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "admin/alluserlist";
    }

    @GetMapping("/users/teachers")
    public String adminTeacherList(Model model) {
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/teacherlist";
    }

    @GetMapping("/users/students")
    public String adminStudentList(Model model) {
        model.addAttribute("students", this.userService.getAllStudents());
        return "admin/studentlist";
    }

    @GetMapping("/courses/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", Course.builder().build());
        model.addAttribute("modes", Mode.values());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/courseform";
    }

    @GetMapping("/courses/edit/{courseId}")
    public String editCourseForm(@PathVariable Long courseId,
                                 Model model) {
        model.addAttribute("course", this.courseService.getCourse(courseId));
        model.addAttribute("modes", Mode.values());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/courseform";
    }

    @PostMapping("/courses/save")
    public String addCourseToDB(@Valid @ModelAttribute Course course,
                                BindingResult result,
                                Model model) {
        if (Objects.nonNull(course.getBeginDate()) &&
            Objects.nonNull(course.getEndDate()) &&
            course.getEndDate().isBefore(course.getBeginDate())) {
            result.rejectValue("endDate", "dates_conflict",
                               "Data zakończenia nie może być wcześniejsza niż data " +
                               "rozpoczęcia");
        }
        if (result.hasErrors()) {
            model.addAttribute("modes", Mode.values());
            model.addAttribute("cities", cityService.getCities());
            model.addAttribute("teachers", this.userService.getAllTeachers());
            return "admin/courseform";
        }
        if (Objects.nonNull(course.getId())) {
            this.courseService.update(course);
        } else {
            this.courseService.save(course);
        }
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/remove/{courseId}")
    public String removeCourse(@PathVariable Long courseId) {
        this.courseService.remove(courseId);
        return "redirect:/admin/courses";
    }

    @GetMapping("/users/add-student")
    public String addStudentForm(Model model) {
        model.addAttribute("user", User.builder()
                                       .role(Role.ROLE_USER)
                                       .hourlyRate(0.0)
                                       .build());
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        return "admin/adduser";
    }

    @GetMapping("/users/add-teacher")
    public String addTeacherForm(Model model) {
        model.addAttribute("user", User.builder().role(Role.ROLE_TEACHER).build());
        return "admin/adduser";
    }

    @PostMapping("/users/add")
    public String processNewUser(@Validated(User.ValidationNew.class)
                                 @ModelAttribute User user,
                                 BindingResult result,
                                 @RequestParam String passwordRepeat,
                                 Model model) {
        if (!user.getPassword().equals(passwordRepeat)) {
            result.rejectValue("password", "passwords_match_err",
                               "Wpisane hasła różnią się");
        }
        if (Objects.nonNull(this.userService.findByEmail(user.getEmail()))) {
            result.rejectValue("email", "email_already_taken",
                               "Konto z takim adresem e-mail już istnieje");
        }
        if (result.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "admin/adduser";
        } else {
            this.userService.saveUser(user);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/users/edit/{userId}")
    public String editTeacher(@PathVariable Long userId, Model model) {
        if (userId > 3) {
            User user = this.userService.getUserById(userId);
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            model.addAttribute("user", user);
            return "admin/edituser";
        } else {
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/users/edit")
    public String saveEditedUser(@Validated(User.ValidationEdited.class)
                                 @ModelAttribute User user,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "admin/edituser";
        }
        this.userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{userId}")
    public String removeUser(@PathVariable Long userId) {
        if (userId > 3) {
            this.userService.delete(userId);
        }
        return "redirect:/admin/users";
    }
}
