package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private List<Course> courses;

    public CourseService() {
        this.initializeCoursesList();
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    private void initializeCoursesList() {
        this.courses = new ArrayList<>();
        this.courses.add(Course.builder().id(1L)
                               .name("Java Developer")
                               .city("Gdańsk")
                               .beginDate(LocalDate.of(2020, 11, 14))
                               .endDate(LocalDate.of(2021, 7, 18))
                               .mode("weekendowy")
                               .price(7999)
                               .teacher(Teacher.builder().firstName("Adam")
                                               .lastName("Kowalski").build())
                               .build());
        this.courses.add(Course.builder().id(2L)
                               .name("C++ od podstaw")
                               .city("Warszawa")
                               .beginDate(LocalDate.of(2020, 12, 7))
                               .endDate(LocalDate.of(2021, 8, 18))
                               .mode("weekendowy")
                               .price(6999)
                               .teacher(Teacher.builder().firstName("Kamil")
                                               .lastName("Kros").build())
                               .build());
        this.courses.add(Course.builder().id(3L)
                               .name("Angular Developer")
                               .city("Wrocław")
                               .beginDate(LocalDate.of(2020, 12, 14))
                               .endDate(LocalDate.of(2021, 3, 15))
                               .mode("dzienny")
                               .price(4999)
                               .teacher(Teacher.builder().firstName("Jan")
                                               .lastName("Nowak").build())
                               .build());
        this.courses.add(Course.builder().id(4L)
                               .name("Tester manualny")
                               .city("Gdańsk")
                               .beginDate(LocalDate.of(2021, 1, 14))
                               .endDate(LocalDate.of(2021, 5, 25))
                               .mode("dzienny")
                               .price(4499)
                               .teacher(Teacher.builder().firstName("Adam")
                                               .lastName("Kowalski").build())
                               .build());
    }
}
