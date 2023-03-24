package com.sexybot.bot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.sexybot.bot.config.TelegramBotApi;
import com.sexybot.bot.scenario.ScenarioResolver;
import com.sexybot.bot.truthOrDare.TruthOrDareResolver;
import com.sexybot.bot.util.BotUtil;
import com.sexybot.bot.util.Pair;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.SimpleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartResolver {
    private final TelegramBotApi telegramBotApi;
    public static final String START_COMMAND = "start";

    private static final List<StringPair> OPTIONS = List.of(StringPair.of("Правда или Действие", TruthOrDareResolver.TRUTH_OR_DARE_COMMAND), StringPair.of("Сценарии", ScenarioResolver.SCENARIO_COMMAND));
    private static final String TEXT = "Выберите действие";

    public void resolve(Update update) {
        Long chatId = BotUtil.getChatId(update);
        SimpleMessage simpleMessage = new SimpleMessage(chatId, TEXT, OPTIONS);
        telegramBotApi.editMessage(simpleMessage, BotUtil.getPushedMessageId(update));
    }
}
