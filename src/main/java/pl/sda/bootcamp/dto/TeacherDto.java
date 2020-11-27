package pl.sda.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.bootcamp.model.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double hourlyRate;
    private String rawPassword;
}
