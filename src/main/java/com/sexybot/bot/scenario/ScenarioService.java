package com.sexybot.bot.scenario;


import com.sexybot.bot.scenario.model.Scenario;
import com.sexybot.model.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;

    public Scenario getRandomScenario() {
        List<Scenario> all = scenarioRepository.findAll();
        int size = all.size();
        return all.get(RandomUtils.nextInt(0, size));
    }

    public Scenario getRandomScenario(List<String> category) {
        List<Scenario> all = scenarioRepository.findAll();
        all = all.stream()
                .filter(it -> it.getCategories()
                        .stream()
                        .map(Category::getName)
                        .allMatch(category::contains))
                .toList();
        int size = all.size();
        return all.get(RandomUtils.nextInt(0, size));
    }
}
