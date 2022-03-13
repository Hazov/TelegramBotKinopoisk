package com.hazov.telegrambot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TelegramKinoBot extends TelegramWebhookBot {

    String botUsername;
    String botToken;
    String botPath;

    public TelegramKinoBot(DefaultBotOptions defaultBotOptions){
        super(defaultBotOptions);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        String chatId = update.getMessage().getFrom().getId().toString();


        StringBuilder message = new StringBuilder();
        message.append("Итак, следующий случайный фильм: \n");


        return new SendMessage(chatId, message.toString());

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }
}
