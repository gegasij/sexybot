package com.sexybot.bot.scenario;

import com.pengrad.telegrambot.model.Update;
import com.sexybot.bot.config.TelegramBotApi;
import com.sexybot.bot.scenario.model.Scenario;
import com.sexybot.bot.util.BotUtil;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.SimpleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScenarioResolver {
    public static final String SCENARIO_COMMAND = "scenario";
    private final ScenarioService scenarioService;
    private final TelegramBotApi telegramBotApi;

    public void resolveScenario(Update update) {
        if (BotUtil.getParameter2(update) == null) {
            List<StringPair> levelMenu = ScenarioUtil.getLevelMenu();
            SimpleMessage simpleMessage = new SimpleMessage(BotUtil.getChatId(update), "Выберите сложность сценария", levelMenu);
            telegramBotApi.editMessage(simpleMessage, BotUtil.getPushedMessageId(update));
            return;
        }
        Scenario randomScenario = scenarioService.getRandomScenario();
        String messageByScenario = ScenarioUtil.getMessageByScenario(randomScenario);
        SimpleMessage simpleMessage = new SimpleMessage(BotUtil.getChatId(update), messageByScenario, ScenarioUtil.getScenarioMenu());
        telegramBotApi.editMessage(simpleMessage, BotUtil.getPushedMessageId(update));
    }
}
