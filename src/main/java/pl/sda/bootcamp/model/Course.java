package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class Course {
    private Integer id;
    private String name;
    private String city;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String mode;
    private Integer price;
    private String teacher;
}
