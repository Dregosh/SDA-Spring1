package pl.sda.bootcamp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Podaj imię")
    private String firstName;

    @NotEmpty(message = "{pl.sda.bootcamp.model.User.lastName.NotEmpty}")
    private String lastName;

    @NotEmpty
    private String email;

    private String phone;

    private Double hourlyRate;

    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherForCourses;

    @ManyToMany
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
