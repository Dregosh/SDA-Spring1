package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder*/
public class Student {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;*/

   /* @ManyToMany
    private List<Course> courses;*/

    /*@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;*/
}
