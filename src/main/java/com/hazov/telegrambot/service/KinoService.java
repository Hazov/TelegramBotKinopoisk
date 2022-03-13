package com.hazov.telegrambot.service;

import com.hazov.telegrambot.payload.as_client.response.kinopoisk.FilmResponse;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Data
@ConfigurationProperties(prefix = "kinopoisk")

public class KinoService {
    @Autowired
    RestTemplate restTemplate;

    Random random = new Random();

    String apiUrl;
    String token;

    short filmRequestCount = 0;
    short MAX_ATTEMPTS = 10;
    ResponseEntity<FilmResponse> responseEntity;
    Queue<FilmResponse> queue = new LinkedList<>();

    public void getRandomFilm() throws Exception {
        //addition headers
        HttpHeaders headers = new HttpHeaders();
        List<String> apiKey = Collections.singletonList(token);
        headers.put("X-API-KEY", apiKey);

        //try to request
        String requestUrl = "";
        do {
            try {
                requestUrl = apiUrl + "films/" + getRandomFilmId();
                responseEntity = sendFilmRequest(requestUrl, headers);
            } catch (HttpClientErrorException e) {
                filmRequestCount++;
                continue;
            }
            filmRequestCount = 0;
            break;
        } while (filmRequestCount < MAX_ATTEMPTS);

        if(responseEntity != null)
        queue.add(responseEntity.getBody());
    }

    //request to kinopoisk
    ResponseEntity<FilmResponse> sendFilmRequest(String requestUrl, HttpHeaders headers) {
        return restTemplate.exchange(
                requestUrl, HttpMethod.GET, new HttpEntity<>(headers), FilmResponse.class);

    }

    private int getRandomFilmId() {
        return random.nextInt(1_500_000);
    }
}
