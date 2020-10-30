package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Teacher {
    private Long id;
    private String firstName;
    private String lastName;
    private Double hourlyRate;
}
