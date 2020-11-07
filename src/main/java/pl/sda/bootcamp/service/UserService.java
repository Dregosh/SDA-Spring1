package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.repository.UserRepository;

import java.util.List;

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

    public List<User> getUsersByRoleName(String roleName) {
        return this.userRepository.findByRole_RoleNameContains(roleName);
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }
}
