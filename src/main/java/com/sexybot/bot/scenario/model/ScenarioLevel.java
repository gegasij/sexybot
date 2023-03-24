package com.sexybot.bot.scenario.model;

import lombok.Getter;

@Getter
public enum ScenarioLevel {
    LIGHT(0, "Light"), MEDIUM(1, "Medium"), HARD(2, "Hard"), CRAZY(3, "Crazy");
    private final int value;
    private final String text;

    ScenarioLevel(int i, String hard) {
        value = i;
        text = hard;
    }

}
