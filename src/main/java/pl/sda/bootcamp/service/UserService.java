package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.repository.CourseRepository;
import pl.sda.bootcamp.repository.UserRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public List<User> getAllStudents() {
        return this.userRepository.findByRole_RoleNameContains("user");
    }

    public List<User> getAllTeachers() {
        return this.userRepository.findByRole_RoleNameContains("teacher");
    }

    public List<User> getUsersByRoleName(String roleName) {
        return this.userRepository.findByRole_RoleNameContains(roleName);
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public void updateTeacher(User user) {
        this.courseRepository.findAll().forEach(course -> {
            if (user.getTeacherForCourses().contains(course)) {
                course.setTeacher(user);
            } else if (course.getTeacher() != null &&
                       user.getId().equals(course.getTeacher().getId())) {
                course.setTeacher(null);
            }
            this.courseRepository.save(course);
        });
        this.userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        for (Course course : user.getTeacherForCourses()) {
            course.setTeacher(null);
            this.courseRepository.save(course);
        }
        this.userRepository.deleteById(id);
    }
}
