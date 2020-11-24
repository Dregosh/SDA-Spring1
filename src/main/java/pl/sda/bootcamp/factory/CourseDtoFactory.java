package pl.sda.bootcamp.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.dto.CourseDto;
import pl.sda.bootcamp.dto.UserDto;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CourseDtoFactory {

    private final UserDtoFactory userDtoFactory;

    public CourseDto create(Course course) {
        return CourseDto.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .city(course.getCity())
                        .mode(course.getMode())
                        .beginDate(course.getBeginDate())
                        .endDate(course.getEndDate())
                        .price(course.getPrice())
                        .teacher(getTeacher(course))
                        .courseUsers(getCourseUsers(course.getUsers()))
                        .build();
    }

    private UserDto getTeacher(Course course) {
        return nonNull(course.getTeacher()) ?
               userDtoFactory.create(course.getTeacher()) :
               null;
    }

    private List<UserDto> getCourseUsers(final List<User> users) {
        if (isNull(users)) {
            return Collections.emptyList();
        }
        return users.stream()
                    .map(userDtoFactory::create)
                    .collect(Collectors.toList());
    }
}
