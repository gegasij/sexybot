package com.converter.text.service;

import com.converter.text.model.ConvertedText;
import com.converter.text.repository.ConvertedTextRepository;
import com.converter.text.service.scenario.Scenario;
import com.converter.text.service.scenario.ScenarioRepository;
import com.converter.text.service.truthOrDare.TruthOrDare;
import com.converter.text.service.truthOrDare.TruthOrDareRepository;
import com.sexybot.bot.scenario.model.ScenarioLevel;
import com.sexybot.bot.truthOrDare.model.LevelTOD;
import com.sexybot.bot.truthOrDare.model.TypeTOD;
import com.sexybot.model.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MergeService {
    private final TruthOrDareRepository truthOrDareRepository;
    private final ConvertedTextRepository convertedTextRepository;
    private final ScenarioRepository scenarioRepository;

    public void mergeTruthOrDare() {
        List<ConvertedText> convertedTexts = convertedTextRepository.findAll()
                .stream()
                .filter(it -> it.getData().containsKey("type"))
                .filter(it -> it.getData().get("type").equals("truthOrDare"))
                .toList();

        for (ConvertedText convertedText : convertedTexts) {
            String forWho = convertedText.getData().get("forWho").toString();
            String level = convertedText.getData().get("level").toString();
            String typeTOD = convertedText.getData().get("typeTOD").toString();

            TruthOrDare truthOrDare = new TruthOrDare();
            truthOrDare.setText(convertedText.getData().get("data").toString());

            Sex sex = getSex(forWho);
            LevelTOD levelTOD = getLevelTOD(level);
            TypeTOD typeTOD1 = getTypeTOD(typeTOD);
            Objects.requireNonNull(sex);
            Objects.requireNonNull(levelTOD);
            Objects.requireNonNull(typeTOD1);
            truthOrDare.setSex(sex.getValue());
            truthOrDare.setLevelTOD(levelTOD.getValue());
            truthOrDare.setTypeTOD(typeTOD1.getValue());
            truthOrDareRepository.save(truthOrDare);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void mergeScenario() {
        List<ConvertedText> convertedTexts = convertedTextRepository.findAll()
                .stream()
                .filter(it -> it.getData().containsKey("type"))
                .filter(it -> it.getData().get("type").equals("scenario"))
                .toList();
        for (ConvertedText convertedText : convertedTexts) {
            Object level = convertedText.getData().get("level");
            ScenarioLevel scenarioLevel = Arrays.stream(ScenarioLevel.values()).filter(it -> it.getText().equalsIgnoreCase(level.toString()))
                    .findAny().get();

            Object data = convertedText.getData().get("data");
            Scenario scenario = new Scenario();
            scenario.setText(data.toString());
            scenario.setScenarioLevel(scenarioLevel.getValue());
            scenarioRepository.save(scenario);
        }
    }

    private static TypeTOD getTypeTOD(String typeTOD) {
        if (typeTOD.equalsIgnoreCase("truth")) {
            return TypeTOD.TRUTH;
        }
        if (typeTOD.equalsIgnoreCase("dare")) {
            return TypeTOD.DARE;
        }
        return null;
    }

    private static LevelTOD getLevelTOD(String level) {
        if (level.equalsIgnoreCase("hard")) {
            return LevelTOD.HARD;
        }
        if (level.equalsIgnoreCase("medium")) {
            return LevelTOD.MEDIUM;
        }
        return null;
    }

    private static Sex getSex(String forWho) {
        if (forWho.equalsIgnoreCase("male")) {
            return Sex.MALE;
        }
        if (forWho.equalsIgnoreCase("female")) {
            return Sex.FEMALE;
        }
        return null;
    }
}
