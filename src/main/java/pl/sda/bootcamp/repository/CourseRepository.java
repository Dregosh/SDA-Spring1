package pl.sda.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.bootcamp.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT DISTINCT c FROM Course c " +
                   "JOIN FETCH c.users " +
                   "JOIN FETCH c.teacher")
    List<Course> deepFind();

    @Query(value = "SELECT c FROM Course c " +
                   "LEFT JOIN FETCH c.users " +
                   "LEFT JOIN FETCH c.teacher " +
                   "WHERE c.id = :id")
    Course deepFindById(Long id);
}
