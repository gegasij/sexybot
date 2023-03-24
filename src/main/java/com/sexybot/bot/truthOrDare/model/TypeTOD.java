package com.sexybot.bot.truthOrDare.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum TypeTOD {
    TRUTH(0), DARE(1);
    private final Integer value;

    public static TypeTOD fromString(String str) {
        return Arrays.stream(TypeTOD.values())
                .filter(it -> Objects.equals(Integer.toString(it.value),str))
                .findAny()
                .orElse(null);
    }
    public static TypeTOD fromInteger(Integer str) {
        return Arrays.stream(TypeTOD.values())
                .filter(it -> it.getValue().equals(str))
                .findAny()
                .orElse(null);
    }
    TypeTOD(int i) {
        value = i;
    }
}
