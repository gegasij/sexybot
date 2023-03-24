package com.sexybot.bot.truthOrDare;

import com.sexybot.bot.StartResolver;
import com.sexybot.bot.truthOrDare.model.LevelTOD;
import com.sexybot.bot.truthOrDare.model.TypeTOD;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.Sex;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class Consts {
    protected static final String TRUTH_OR_DARE_COMMAND = "truthOrDare";

    protected static StringPair maleCommand = StringPair.of("Парень", "%s %s".formatted(TRUTH_OR_DARE_COMMAND, Sex.MALE.getValue()));
    protected static StringPair femaleCommand = StringPair.of("Девушка", "%s %s".formatted(TRUTH_OR_DARE_COMMAND, Sex.FEMALE.getValue()));
    protected static StringPair randomCommand = StringPair.of("Случайно", "%s %s".formatted(TRUTH_OR_DARE_COMMAND, Sex.MALE.getValue()));
    protected static StringPair BACK = StringPair.of("назад", StartResolver.START_COMMAND);
    protected static List<StringPair> START_OPTION = List.of(
            maleCommand,
            femaleCommand,
            randomCommand,
            BACK
    );
    protected static final String START_MENU = "Кто начнет?";

    protected static final String MENU_2 = "Выберите опцию";

    public static String truthCommandValue = "Правда";
    public static String truthHardCommandValue = "Правда Hard";
    public static String DareCommandValue = "Действие";
    public static String DareHardCommandValue = "Действие Hard";

    protected static List<StringPair> getStartMenu() {
        randomCommand = new StringPair("Случайно", "%s %s".formatted(TRUTH_OR_DARE_COMMAND, randomSex().getValue()));
        return START_OPTION;
    }

    protected static List<StringPair> getMenu2(Sex sex) {
        return List.of(
                StringPair.of("Правда", "%s %s %s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue(), TypeTOD.TRUTH.getValue(), LevelTOD.MEDIUM.getValue())),
                StringPair.of("Правда Hard", "%s %s %s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue(), TypeTOD.TRUTH.getValue(), LevelTOD.HARD.getValue())),
                StringPair.of("Действие", "%s %s %s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue(), TypeTOD.DARE.getValue(), LevelTOD.MEDIUM.getValue())),
                StringPair.of("Действие Hard", "%s %s %s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue(), TypeTOD.DARE.getValue(), LevelTOD.HARD.getValue())),
                StringPair.of("Случайно", "%s %s %s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue(), randomTypeTruthOrDare().getValue(), randomLevelTruthOrDare().getValue())),
                StringPair.of("Назад", "%s".formatted(StartResolver.START_COMMAND))
        );
    }

    protected static List<StringPair> getMenu3(Sex sex) {
        return List.of(
                StringPair.of("Дальше", "%s %s".formatted(TRUTH_OR_DARE_COMMAND, sex.getValue()))
        );
    }

    protected static String getMenu2Text(Sex sex) {

        return sex.equals(Sex.MALE) ?
                "Девушка выбирает" : "Парень выбирает";
    }

    protected static Sex nextSex(Sex sex) {
        return sex.equals(Sex.MALE) ? Sex.FEMALE : Sex.MALE;
    }

    protected static Sex randomSex() {
        int i = RandomUtils.nextInt(0, Sex.values().length);
        return Sex.fromInteger(i);
    }

    protected static TypeTOD randomTypeTruthOrDare() {
        int i = RandomUtils.nextInt(0, TypeTOD.values().length);
        return TypeTOD.fromInteger(i);
    }

    protected static LevelTOD randomLevelTruthOrDare() {
        int i = RandomUtils.nextInt(0, LevelTOD.values().length);
        return LevelTOD.fromInteger(i);
    }
}
