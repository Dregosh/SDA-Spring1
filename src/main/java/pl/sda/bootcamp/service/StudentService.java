package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return this.students;
    }
}
