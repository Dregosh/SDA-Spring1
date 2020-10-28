package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class Course {
    private Integer id;
    private String name;
    private String city;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String mode;
}
