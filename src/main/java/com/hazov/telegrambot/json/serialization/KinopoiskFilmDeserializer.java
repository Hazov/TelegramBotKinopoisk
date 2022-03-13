package com.hazov.telegrambot.json.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hazov.telegrambot.payload.as_client.response.kinopoisk.FilmResponse;

import java.io.IOException;
import java.util.List;

public class KinopoiskFilmDeserializer extends JsonDeserializer<FilmResponse> {

    @Override
    public FilmResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode nodeTree = jsonParser.getCodec().readTree(jsonParser);
        String nameRu = nodeTree.get("nameRu").textValue();
        String nameOriginal = nodeTree.get("nameOriginal").textValue();
        String posterUrl = nodeTree.get("posterUrl").textValue();
        float ratingKinopoisk = nodeTree.get("ratingKinopoisk").floatValue();
        float ratingImdb = nodeTree.get("ratingImdb").floatValue();
        String slogan = nodeTree.get("slogan").textValue();
        String description = nodeTree.get("description").textValue();
        String ratingAgeLimits = nodeTree.get("ratingAgeLimits").textValue();
        short year = nodeTree.get("year").shortValue();
        List<String> countries = nodeTree.get("countries").findValuesAsText("country");
        List<String> genres = nodeTree.get("genres").findValuesAsText("genre");
        return new FilmResponse(
                nameRu, nameOriginal, posterUrl,
                ratingKinopoisk, ratingImdb, slogan,
                description, ratingAgeLimits, year, countries, genres);
    }
}
