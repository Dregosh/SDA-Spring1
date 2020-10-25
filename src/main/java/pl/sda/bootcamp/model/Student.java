package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Course course;
}
