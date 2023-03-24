package com.sexybot.bot.truthOrDare;

import com.pengrad.telegrambot.model.Update;
import com.sexybot.bot.config.TelegramBotApi;
import com.sexybot.bot.truthOrDare.model.LevelTOD;
import com.sexybot.bot.truthOrDare.model.TruthOrDare;
import com.sexybot.bot.truthOrDare.model.TypeTOD;
import com.sexybot.bot.util.BotUtil;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.Sex;
import com.sexybot.model.SimpleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TruthOrDareResolver {
    public static final String TRUTH_OR_DARE_COMMAND = "truthOrDare";

    private final TelegramBotApi telegramBotApi;
    private final TruthOrDareService truthOrDareService;

    public void resolve(Update update) {
        Long chatId = BotUtil.getChatId(update);
        String parameter2 = BotUtil.getParameter2(update);
        SimpleMessage simpleMessage = null;
        if (parameter2 == null) {
            simpleMessage = new SimpleMessage(chatId, Consts.START_MENU, Consts.getStartMenu());
        }
        if (parameter2 != null) {
            Sex sex = Sex.fromString(parameter2);
            TypeTOD typeTOD = TypeTOD.fromString(BotUtil.getParameter3(update));
            LevelTOD levelTOD = LevelTOD.fromString(BotUtil.getParameter4(update));

            if (sex != null && typeTOD != null && levelTOD != null) {
                TruthOrDare randomTruthOrDare = truthOrDareService.getRandomTruthOrDare(sex, levelTOD, typeTOD);
                List<StringPair> menu3 = Consts.getMenu3(sex);
                simpleMessage = new SimpleMessage(chatId, randomTruthOrDare.getText(), menu3);
            } else if (sex != null) {
                simpleMessage = new SimpleMessage(chatId, Consts.getMenu2Text(sex), Consts.getMenu2(Consts.nextSex(sex)));
            }
        }
        execute(update, simpleMessage);
    }

    private void execute(Update update, SimpleMessage simpleMessage) {
        if (simpleMessage != null) {
            Integer pushedMessageId = BotUtil.getPushedMessageId(update);
            if (pushedMessageId != null) {
                telegramBotApi.editMessage(simpleMessage, pushedMessageId);
            } else telegramBotApi.sendMessage(simpleMessage);
        }
    }
}
