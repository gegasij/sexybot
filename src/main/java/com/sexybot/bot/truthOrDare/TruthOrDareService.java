package com.sexybot.bot.truthOrDare;

import com.sexybot.bot.truthOrDare.model.LevelTOD;
import com.sexybot.bot.truthOrDare.model.TruthOrDare;
import com.sexybot.bot.truthOrDare.model.TypeTOD;
import com.sexybot.model.Sex;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TruthOrDareService {
    private final TruthOrDareRepository truthOrDareRepository;

    public TruthOrDare getRandomTruthOrDare(Sex sex, LevelTOD levelTOD, TypeTOD typeTOD) {
        Objects.requireNonNull(sex, "second must not be null");
        Objects.requireNonNull(levelTOD, "second must not be null");
        Objects.requireNonNull(typeTOD, "second must not be null");
        List<TruthOrDare> list = truthOrDareRepository.findBySexAndTypeTODAndLevelTOD(sex.getValue(), typeTOD.getValue(), levelTOD.getValue());
        long random = RandomUtils.nextLong(0, list.size());
        return list.get((int) random);
    }
}
