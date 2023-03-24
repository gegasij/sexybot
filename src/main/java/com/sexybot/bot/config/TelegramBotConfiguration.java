package com.sexybot.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.sexybot.bot.UpdateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class TelegramBotConfiguration {

    @Bean
    public TelegramBot lovelyBot() {
        return new TelegramBot("5941334455:AAGqnU3LDd-2el8a7vu4HAIJ0RAi91ApPZE");
    }

}