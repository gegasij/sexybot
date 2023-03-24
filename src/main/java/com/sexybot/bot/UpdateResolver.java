package com.sexybot.bot;

import com.pengrad.telegrambot.model.Update;
import com.sexybot.bot.scenario.ScenarioResolver;
import com.sexybot.bot.truthOrDare.TruthOrDareResolver;
import com.sexybot.bot.util.BotUtil;
import com.sexybot.model.TgUser;
import com.sexybot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateResolver {
    private final UserService userService;
    private final StartResolver startResolver;
    private final TruthOrDareResolver truthOrDareResolver;
    private final ScenarioResolver scenarioResolver;

    public void resolve(Update update) {
        Long chatId = BotUtil.getChatId(update);

        TgUser tgUser = userService.getUserByChatId(chatId)
                .orElseGet(() -> userService.createUser(chatId));

        String command = BotUtil.getParameter1(update);
        if (command == null) {
            return;
        }
        if (BotUtil.isCommand(StartResolver.START_COMMAND, command)) {
            startResolver.resolve(update);
        }
        if (BotUtil.isCommand(TruthOrDareResolver.TRUTH_OR_DARE_COMMAND, command)) {
            truthOrDareResolver.resolve(update);
        }
        if (BotUtil.isCommand(ScenarioResolver.SCENARIO_COMMAND, command)) {
            scenarioResolver.resolveScenario(update);
        }
    }
}
