package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private List<Teacher> teachers;

    public TeacherService() {
        this.initializeTeachersList();
    }

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

    private void initializeTeachersList() {
        this.teachers = new ArrayList<>();
        this.teachers.add(Teacher.builder()
                                 .id(1L)
                                 .firstName("Adam")
                                 .lastName("Kowalski")
                                 .hourlyRate(125.00)
                                 .build());

        this.teachers.add(Teacher.builder()
                                 .id(2L)
                                 .firstName("Kamil")
                                 .lastName("Kros")
                                 .hourlyRate(115.00)
                                 .build());

        this.teachers.add(Teacher.builder()
                                 .id(3L)
                                 .firstName("Jan")
                                 .lastName("Nowak")
                                 .hourlyRate(105.00)
                                 .build());
    }
}
