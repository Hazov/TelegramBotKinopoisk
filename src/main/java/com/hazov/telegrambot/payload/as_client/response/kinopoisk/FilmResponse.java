package com.hazov.telegrambot.payload.as_client.response.kinopoisk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hazov.telegrambot.json.serialization.KinopoiskFilmDeserializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonDeserialize(using = KinopoiskFilmDeserializer.class)
@ToString
public class FilmResponse {
    String nameRu;
    String nameOriginal;
    String posterUrl;
    float ratingKinopoisk;
    float ratingImdb;
    String slogan;
    String description;
    String ratingAgeLimits;
    short year;
    List<String> countries;
    List<String> genres;
}
