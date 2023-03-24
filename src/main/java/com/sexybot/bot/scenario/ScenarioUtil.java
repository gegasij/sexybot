package com.sexybot.bot.scenario;

import com.sexybot.bot.StartResolver;
import com.sexybot.bot.scenario.model.Scenario;
import com.sexybot.bot.scenario.model.ScenarioLevel;
import com.sexybot.bot.util.StringPair;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ScenarioUtil {
    public static String getMessageByScenario(Scenario scenario) {
        String levelCaption = getLevelCaption(scenario.getScenarioLevel());
        String levelText = "<strong>Уровень: %s</strong>".formatted(levelCaption);
        return "%s\n%s".formatted(levelText, scenario.getText());
    }

    public String getLevelCaption(Integer level) {
        return Arrays.stream(ScenarioLevel.values())
                .filter(it -> it.getValue() == level)
                .findAny().map(ScenarioLevel::getText)
                .orElse(ScenarioLevel.MEDIUM.getText());
    }

    public List<StringPair> getLevelMenu() {
        return Arrays.stream(ScenarioLevel.values()).map(ScenarioLevel::getText).map(it -> StringPair.of(it, "%s %s".formatted(ScenarioResolver.SCENARIO_COMMAND, it)))
                .toList();
    }

    public List<StringPair> getScenarioMenu() {
        return List.of(StringPair.of("назад", StartResolver.START_COMMAND), StringPair.of("еще сценарий", ScenarioResolver.SCENARIO_COMMAND));

    }
}
