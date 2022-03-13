package com.hazov.telegrambot.configuration;

import com.hazov.telegrambot.TelegramKinoBot;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableScheduling
public class KinoBotConfiguration {
    private TelegramBotConfigValues botConfig;

    public KinoBotConfiguration(TelegramBotConfigValues telegramBotConfigValues) {
        this.botConfig = telegramBotConfigValues;
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;

    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public TelegramKinoBot telegramBot() {
        DefaultBotOptions options = new DefaultBotOptions();


        options.setProxyHost(botConfig.getProxyHost());
        options.setProxyPort(botConfig.getProxyPort());
        options.setProxyType(botConfig.getProxyType());

        TelegramKinoBot telegramBot = new TelegramKinoBot(options);
        telegramBot.setBotUsername(botConfig.getUserName());
        telegramBot.setBotToken(botConfig.getBotToken());
        telegramBot.setBotPath(botConfig.getWebHookPath());

        return telegramBot;
    }
}
