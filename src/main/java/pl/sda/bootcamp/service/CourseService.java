package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void save(Course course) {
        Course courseToPersist = Course.builder()
                                       .name(course.getName())
                                       .city(course.getCity())
                                       .beginDate(course.getBeginDate())
                                       .endDate(course.getEndDate())
                                       .mode(course.getMode())
                                       .price(course.getPrice())
                                       .teacher(course.getTeacher())
                                       .users(new ArrayList<>())
                                       .build();
        if (Objects.nonNull(course.getUsers())) {
            course.getUsers().forEach(courseToPersist::addUser);
        }
        this.courseRepository.save(course);
    }

    @Transactional
    public void update(Course course) {
        this.courseRepository.findById(course.getId()).ifPresent(courseToPersist -> {
            courseToPersist.setName(course.getName());
            courseToPersist.setCity(course.getCity());
            courseToPersist.setBeginDate(course.getBeginDate());
            courseToPersist.setEndDate(course.getEndDate());
            courseToPersist.setMode(course.getMode());
            courseToPersist.setPrice(course.getPrice());
            courseToPersist.setTeacher(course.getTeacher());
            // TODO: users changes (for now users cannot be changed in course edition)
        });
    }

    public void remove(Long id) {
        this.courseRepository.findById(id).ifPresent(course -> {
            course.removeAllUsers();
            this.courseRepository.delete(course);
        });
    }
}
