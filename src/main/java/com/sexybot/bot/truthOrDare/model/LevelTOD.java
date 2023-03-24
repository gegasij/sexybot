package com.sexybot.bot.truthOrDare.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum LevelTOD {
    MEDIUM(0), HARD(1);
    private final Integer value;

    public static LevelTOD fromString(String str) {
        return Arrays.stream(LevelTOD.values())
                .filter(it -> Objects.equals(Integer.toString(it.value), str))
                .findAny()
                .orElse(null);
    }

    public static LevelTOD fromInteger(Integer str) {
        return Arrays.stream(LevelTOD.values())
                .filter(it -> it.getValue().equals(str))
                .findAny()
                .orElse(null);
    }

    LevelTOD(int i) {
        value = i;
    }
}
