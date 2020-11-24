package pl.sda.bootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.*;
import pl.sda.bootcamp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        return "admin/addcourse";
    }

    @PostMapping("/courses/add")
    public String addCourseToDB(@ModelAttribute Course course) {
        this.courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/edit/{courseId}")
    public String editCourseForm(@PathVariable Long courseId,
                                 Model model,
                                 final HttpSession session) {
        session.setAttribute("courseId", courseId);
        model.addAttribute("course", this.courseService.getCourse(courseId));
        model.addAttribute("modes", Mode.values());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("teachers", this.userService.getAllTeachers());
        return "admin/editcourse";
    }

    @PostMapping("/courses/edit")
    public String saveEditedCourse(@ModelAttribute Course course,
                                   final HttpServletRequest httpServletRequest) {
        Long courseId = (Long) httpServletRequest.getSession().getAttribute("courseId");
        course.setId(courseId);
        this.courseService.update(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/remove/{courseId}")
    public String removeCourse(@PathVariable Long courseId) {
        this.courseService.remove(courseId);
        return "redirect:/admin/courses";
    }

    @GetMapping("/users/teachers/add")
    public String addTeacherForm(Model model) {
        model.addAttribute("user", User.builder().build());
        return "admin/addteacher";
    }

    @PostMapping("/users/teachers/add")
    public String addTeacherToDB(@Valid @ModelAttribute User user,
                                 BindingResult result,
                                 @RequestParam String passwordRepeat) {
        if (!user.getPassword().equals(passwordRepeat)) {
            result.rejectValue("password", "passwords_match_err",
                               "Wpisane hasła różnią się");
        }
        if (result.hasErrors()) {
            return "admin/addteacher";
        } else {
            user.setRole(Role.ROLE_TEACHER);
            this.userService.saveUser(user);
            return "redirect:/admin/users/teachers";
        }
    }

    @GetMapping("/users/edit/{userId}")
    public String editUser(@PathVariable Long userId,
                           final HttpSession session) {
        session.setAttribute("userId", userId);
        return this.userService.getUserById(userId)
                               .getRole().equals(Role.ROLE_TEACHER) ?
               "redirect:/admin/users/teachers/edit" :
               "redirect:/admin/users/students/edit";
    }

    @GetMapping("/users/teachers/edit")
    public String editTeacher(final HttpServletRequest httpServletRequest,
                              Model model) {
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        User user = this.userService.getUserById(userId);
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        model.addAttribute("user", user);
        return "admin/editteacher";
    }

    @PostMapping("/users/teachers/edit")
    public String saveEditedTeacher(@Valid @ModelAttribute User user,
                                    BindingResult result,
                                    final HttpServletRequest httpServletRequest,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "admin/editteacher";
        }
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        user.setId(userId);
        this.userService.updateUser(user);
        httpServletRequest.getSession().invalidate();
        return "redirect:/admin/users/teachers";
    }

    @GetMapping("/users/students/add")
    public String addStudent(Model model) {
        model.addAttribute("user", User.builder().build());
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        return "admin/addstudent";
    }

    @PostMapping("/users/students/add")
    public String addStudentToDB(@Valid @ModelAttribute User user,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "admin/addstudent";
        } else {
            user.setRole(Role.ROLE_USER);
            this.userService.saveUser(user);
            return "redirect:/admin/users/students";
        }
    }

    @GetMapping("/users/students/edit")
    public String editStudent(final HttpServletRequest httpServletRequest,
                              Model model) {
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        User user = this.userService.getUserById(userId);
        model.addAttribute("coursesList", this.courseService.getAllCourses());
        model.addAttribute("user", user);
        return "admin/editstudent";
    }

    @PostMapping("/users/students/edit")
    public String saveEditedStudent(@Valid @ModelAttribute User user,
                                    BindingResult result,
                                    final HttpServletRequest httpServletRequest,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("coursesList", this.courseService.getAllCourses());
            return "admin/editstudent";
        }
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        user.setId(userId);
        this.userService.updateUser(user);
        httpServletRequest.getSession().invalidate();
        return "redirect:/admin/users/students";
    }

    @GetMapping("/users/delete/{userId}")
    public String removeUser(@PathVariable Long userId) {
        this.userService.delete(userId);
        return "redirect:/admin/users";
    }
}
