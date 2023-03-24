package com.sexybot.service;

import com.pengrad.telegrambot.request.BaseRequest;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.SimpleMessage;
import com.sexybot.model.TgUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentResolver {
    private static final String message = "Вы не можете дальше пользоваться ботом пока не оплатите подписку";

    public SimpleMessage resolvePayment(TgUser user) {

        if (!user.isPayed()) {
            if (user.getJoinedDateTime().plusMinutes(30).isBefore(LocalDateTime.now())) {
                SimpleMessage simpleMessage = new SimpleMessage(user.getChatId(), message, List.of(StringPair.of("оплатить", "start")));
            }
        }
        return null;
    }

}
