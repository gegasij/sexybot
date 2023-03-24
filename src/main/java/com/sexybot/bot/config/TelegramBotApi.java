package com.sexybot.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.sexybot.bot.util.StringPair;
import com.sexybot.model.SimpleMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelegramBotApi {
    public TelegramBotApi(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    private final TelegramBot telegramBot;

    public BaseResponse sendMessage(BaseRequest<?, ?> editMessageText) {
        return telegramBot.execute(editMessageText);
    }

    public BaseResponse sendMessage(SimpleMessage simpleMessage) {
        SendMessage sendMessage = new SendMessage(simpleMessage.getChatId(), simpleMessage.getText());

        if (simpleMessage.getOptions() != null) {
            addKeyboard(simpleMessage.getOptions(), sendMessage);
        }
        sendMessage.parseMode(ParseMode.HTML);
        return telegramBot.execute(sendMessage);
    }

    public BaseResponse editMessage(SimpleMessage simpleMessage, Integer editMessageId) {
        if (editMessageId == null) {
            return sendMessage(simpleMessage);
        }
        EditMessageText editMessageText = new EditMessageText(simpleMessage.getChatId(), editMessageId, simpleMessage.getText());
        if (simpleMessage.getOptions() != null) {
            addKeyboard(simpleMessage.getOptions(), editMessageText);
        }
        editMessageText.parseMode(ParseMode.HTML);
        return telegramBot.execute(editMessageText);
    }

    private static void addKeyboard(List<StringPair> options, SendMessage sendMessage) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        options
                .stream()
                .map(it -> new InlineKeyboardButton(it.getFirst()).callbackData(it.getSecond()))
                .forEach(keyboardMarkup::addRow);

        sendMessage.replyMarkup(keyboardMarkup);
    }

    private static void addKeyboard(List<StringPair> options, EditMessageText sendMessage) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        options
                .stream()
                .map(it -> new InlineKeyboardButton(it.getFirst()).callbackData(it.getSecond()))
                .forEach(keyboardMarkup::addRow);

        sendMessage.replyMarkup(keyboardMarkup);
    }

}
