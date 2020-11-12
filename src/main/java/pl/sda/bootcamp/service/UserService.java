package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

    public void saveUser(User user) {
        User userToPersist = User.builder()
                                 .firstName(user.getFirstName())
                                 .lastName(user.getLastName())
                                 .email(user.getEmail())
                                 .phone(user.getPhone())
                                 .hourlyRate(user.getHourlyRate())
                                 .teacherForCourses(new ArrayList<>())
                                 .courses(new ArrayList<>())
                                 .role(user.getRole())
                                 .build();
        if (Objects.isNull(userToPersist.getHourlyRate())) {
            userToPersist.setHourlyRate(0.0);
        }
        if (Objects.nonNull(user.getTeacherForCourses())) {
            user.getTeacherForCourses().forEach(userToPersist::addTeacherCourse);
        }
        if (Objects.nonNull(user.getCourses())) {
            userToPersist.setCourses(user.getCourses());
        }
        this.userRepository.save(userToPersist);
    }

    @Transactional
    public void updateUser(User user) {
        this.userRepository.findById(user.getId()).ifPresent(persistedUser -> {
            persistedUser.setFirstName(user.getFirstName());
            persistedUser.setLastName(user.getLastName());
            persistedUser.setEmail(user.getEmail());
            persistedUser.setPhone(user.getPhone());
            if (Objects.nonNull(user.getHourlyRate())) {
                persistedUser.setHourlyRate(user.getHourlyRate());
            }
            if (Objects.nonNull(user.getTeacherForCourses())) {
                persistedUser.removeAllTeacherCourses();
                user.getTeacherForCourses().forEach(persistedUser::addTeacherCourse);
            }
            if (Objects.nonNull(user.getCourses())) {
                persistedUser.setCourses(user.getCourses());
            }
        });
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.findById(id).ifPresent(user -> {
            user.removeAllTeacherCourses();
            this.userRepository.delete(user);
        });
    }
}
