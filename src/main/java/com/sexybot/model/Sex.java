package com.sexybot.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Sex {
    MALE(0), FEMALE(1);
    private final Integer value;

    public static Sex fromString(String str) {
        return Arrays.stream(Sex.values())
                .filter(it -> Objects.equals(Integer.toString(it.value),str))
                .findAny()
                .orElse(null);
    }

    public static Sex fromInteger(Integer str) {
        return Arrays.stream(Sex.values())
                .filter(it -> it.getValue().equals(str))
                .findAny()
                .orElse(null);
    }

    Sex(int i) {
        value = i;
    }
}
