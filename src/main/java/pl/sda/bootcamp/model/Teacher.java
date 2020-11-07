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
public class Teacher {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Double hourlyRate;*/

    /*@OneToMany(mappedBy = "teacher")
    private List<Course> courseList;*/
}
