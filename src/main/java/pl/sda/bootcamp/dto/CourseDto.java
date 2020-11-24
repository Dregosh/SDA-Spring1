package pl.sda.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.bootcamp.model.Mode;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    private String name;
    private String city;
    private Mode mode;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer price;
    private UserDto teacher;
    private List<UserDto> courseUsers;
}
