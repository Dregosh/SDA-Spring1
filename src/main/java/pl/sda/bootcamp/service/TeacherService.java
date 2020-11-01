package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Student;
import pl.sda.bootcamp.model.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private List<Teacher> teachers;
    private Long nextId;

    public TeacherService() {
        this.initializeTeachersList();
        this.nextId = 5L;
    }

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

    public Teacher getTeacher(Long id) {
        return this.teachers.stream()
                            .filter(t -> t.getId().equals(id))
                            .findAny()
                            .orElse(null);
    }

    public void addTeacher(Teacher teacher) {
        teacher.setId(this.getNextIdAndIncrement());
        this.teachers.add(teacher);
    }

    public Teacher removeTeacher(Long id) {
        Teacher removedTeacher = this.getTeacher(id);
        if (removedTeacher != null) {
            this.teachers.remove(removedTeacher);
        }
        return removedTeacher;
    }

    public Long getNextIdAndIncrement() {
        return nextId++;
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

        this.teachers.add(Teacher.builder()
                                 .id(4L)
                                 .firstName("Marek")
                                 .lastName("Sielski")
                                 .hourlyRate(95.00)
                                 .build());
    }
}
