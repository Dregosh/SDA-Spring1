package pl.sda.bootcamp.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.dto.CourseDto;
import pl.sda.bootcamp.dto.TeacherDto;
import pl.sda.bootcamp.factory.CourseDtoFactory;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Role;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.repository.CourseRepository;
import pl.sda.bootcamp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    private final CourseRepository courseRepository;
    private final CourseDtoFactory courseDtoFactory;
    private final UserService userService;

    @ResponseBody
    @GetMapping("/course/all")
    public List<CourseDto> getCourses() {
        List<Course> courses = this.courseRepository.deepFind();
        return courses.stream()
               .map(courseDtoFactory::create)
               .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/course/{id}")
    public CourseDto getSingleCourse(@PathVariable Long id) {
        Course course = this.courseRepository.deepFindById(id);
        return courseDtoFactory.create(course);
    }

    @PutMapping("/teacher/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@RequestBody TeacherDto teacherDto) {
        User user = User.builder()
                        .firstName(teacherDto.getFirstName())
                        .lastName(teacherDto.getLastName())
                        .email(teacherDto.getEmail())
                        .phone(teacherDto.getPhone())
                        .hourlyRate(teacherDto.getHourlyRate())
                        .role(Role.ROLE_TEACHER)
                        .password(teacherDto.getRawPassword())
                        .build();
        this.userService.saveUser(user);
    }
}
