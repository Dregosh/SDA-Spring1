package pl.sda.bootcamp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "imię")
    private String firstName;

    @NotEmpty(message = "{pl.sda.bootcamp.model.User.lastName.NotEmpty}")
    private String lastName;

    @NotEmpty(message = "e-mail")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "telefon")
    private String phone;

    private Double hourlyRate;

    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherForCourses;

    @ManyToMany
    private List<Course> courses;

    /*@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;*/

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    public void addTeacherCourse(Course course) {
        course.setTeacher(this);
        this.teacherForCourses.add(course);
    }

    public void removeTeacherCourse(Course course) {
        course.setTeacher(null);
        this.teacherForCourses.remove(course);
    }

    public void removeAllTeacherCourses() {
        Iterator<Course> it = this.teacherForCourses.iterator();
        while (it.hasNext()) {
            it.next().setTeacher(null);
            it.remove();
        }
    }
}
