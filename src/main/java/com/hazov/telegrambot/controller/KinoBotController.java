package com.hazov.telegrambot.controller;

import com.hazov.telegrambot.TelegramKinoBot;
import com.hazov.telegrambot.service.KinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class KinoBotController {
    @Autowired
    KinoService kinoService;

    private final TelegramKinoBot telegramBot;

    public KinoBotController(TelegramKinoBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

    @RequestMapping(value = "/subscribers", method = RequestMethod.POST)
    public BotApiMethod<?> getSubscribers(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
