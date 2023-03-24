package com.sexybot.model;

import com.sexybot.bot.util.StringPair;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class SimpleMessage {

    private final Long chatId;
    private final String text;
    private final List<StringPair> options;

    public SimpleMessage(Long chatId, String text, List<StringPair> options) {
        Objects.requireNonNull(chatId, "chatId must not be null");
        Objects.requireNonNull(text, "text must not be null");
        Objects.requireNonNull(options, "options must not be null");
        this.chatId = chatId;
        this.text = text;
        this.options = options;
    }

    public SimpleMessage(Long chatId, String text) {
        Objects.requireNonNull(chatId, "chatId must not be null");
        Objects.requireNonNull(text, "text must not be null");
        this.chatId = chatId;
        this.text = text;
        this.options = null;
    }
}
