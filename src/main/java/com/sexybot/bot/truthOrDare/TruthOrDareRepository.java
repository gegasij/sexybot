package com.sexybot.bot.truthOrDare;

import com.sexybot.bot.truthOrDare.model.TruthOrDare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruthOrDareRepository extends JpaRepository<TruthOrDare, Integer> {
    List<TruthOrDare> findBySexAndTypeTODAndLevelTOD(Integer sex, Integer type, Integer level);
}
