package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN("Administrator"), ROLE_USER("Student"), ROLE_TEACHER("Trener");

    private final String roleName;
}
