package pl.sda.bootcamp.model;

import lombok.*;

import javax.persistence.*;
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

    private String firstName;

    private String lastName;

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
