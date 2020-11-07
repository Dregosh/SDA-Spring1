package pl.sda.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mode {
    DAILY("dzienny"),
    EXTRAMURAL("zaoczny");

    private final String description;
}
