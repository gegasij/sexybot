package com.converter.text.service.truthOrDare;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruthOrDareRepository extends JpaRepository<TruthOrDare, Integer> {
    List<TruthOrDare> findBySexAndTypeTODAndLevelTOD(Integer sex, Integer type, Integer level);
}
