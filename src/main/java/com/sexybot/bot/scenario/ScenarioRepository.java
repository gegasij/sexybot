package com.sexybot.bot.scenario;

import com.sexybot.bot.scenario.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ScenarioRepository extends JpaRepository<Scenario, Integer> {

}
