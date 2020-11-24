package pl.sda.bootcamp.factory;

import org.springframework.stereotype.Service;
import pl.sda.bootcamp.dto.UserDto;
import pl.sda.bootcamp.model.User;

@Service
public class UserDtoFactory {

    public UserDto create(User user) {
        return UserDto.builder()
                      .id(user.getId())
                      .firstName(user.getFirstName())
                      .lastName(user.getLastName())
                      .email(user.getEmail())
                      .phone(user.getPhone())
                      .hourlyRate(user.getHourlyRate())
                      .build();
    }
}
