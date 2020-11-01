package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students;
    private Long nextId;

    public StudentService() {
        this.students = new ArrayList<>();
        this.nextId = 5L;
        this.initializeStudentList();
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public Student getStudent(Long id) {
        return this.students.stream()
                            .filter(s -> s.getId().equals(id))
                            .findAny()
                            .orElse(null);
    }

    public void addStudent(Student student) {
        student.setId(this.getNextIdAndIncrement());
        this.students.add(student);
    }

    public Student removeStudent(Long id) {
        Student removedStudent = this.getStudent(id);
        if (removedStudent != null) {
            this.students.remove(removedStudent);
        }
        return removedStudent;
    }

    public Long getNextIdAndIncrement() {
        return nextId++;
    }

    private void initializeStudentList() {
        this.students.add(Student.builder()
                                 .id(1L)
                                 .firstName("Jacek")
                                 .lastName("Pająk")
                                 .email("jp@wp.pl")
                                 .phone("123456789")
                                 .course(Course.builder()
                                               .name("Java Developer")
                                               .build())
                                 .build());

        this.students.add(Student.builder()
                                 .id(2L)
                                 .firstName("Wiktoria")
                                 .lastName("Lis")
                                 .email("wiki1@onet.pl")
                                 .phone("987654321")
                                 .course(Course.builder()
                                               .name("Angular Developer")
                                               .build())
                                 .build());

        this.students.add(Student.builder()
                                 .id(3L)
                                 .firstName("Natasza")
                                 .lastName("Makowska")
                                 .email("natmak@gmail.com")
                                 .phone("741852963")
                                 .course(Course.builder()
                                               .name("C++ od podstaw")
                                               .build())
                                 .build());

        this.students.add(Student.builder()
                                 .id(4L)
                                 .firstName("Michał")
                                 .lastName("Milewski")
                                 .email("michal1995@onet.pl")
                                 .phone("987654321")
                                 .course(Course.builder()
                                               .name("Java Developer")
                                               .build())
                                 .build());
    }
}
