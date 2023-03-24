package com.sexybot.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.sexybot.bot.UpdateResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateListenerImpl implements UpdatesListener {
    private final UpdateResolver updateResolver;

    public UpdateListenerImpl(UpdateResolver updateResolver, TelegramBot telegramBot) {
        this.updateResolver = updateResolver;
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        long startTime = System.currentTimeMillis();
        Update update = updates.get(0);
        try {
            updateResolver.resolve(update);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        System.out.println("time processing " + ((float) (System.currentTimeMillis() - startTime)) / 1000);
        return update.updateId();
    }

}

