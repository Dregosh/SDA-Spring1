package pl.sda.bootcamp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotEmpty(message = "Proszę podać imię",
              groups = {ValidationNew.class, ValidationEdited.class})
    private String firstName;

    @NotEmpty(message = "Proszę podać nazwisko",
              groups = {ValidationNew.class, ValidationEdited.class})
    private String lastName;

    @NotEmpty(message = "Proszę podać adres e-mail",
              groups = {ValidationNew.class, ValidationEdited.class})
    @Email(message = "Niepoprawny adres e-mail",
           groups = {ValidationNew.class, ValidationEdited.class})
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Proszę podać telefon",
              groups = {ValidationNew.class, ValidationEdited.class})
    private String phone;

    @NotNull(message = "Proszę podać stawkę godzinową",
             groups = {ValidationNew.class, ValidationEdited.class})
    @Min(value = 0, message = "Stawka nie może być ujemna",
         groups = {ValidationNew.class, ValidationEdited.class})
    private Double hourlyRate;

    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherForCourses;

    @ManyToMany
    private List<Course> courses;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotEmpty(message = "Proszę uzupełnić hasło",
              groups = {ValidationNew.class})
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

    public interface ValidationNew { }
    public interface ValidationEdited { }
}
