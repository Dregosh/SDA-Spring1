package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.repository.CourseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return this.courseRepository.findById(id).orElse(null);
    }

    public void addCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course removeCourse(Long id) {
        return null;
    }
}
