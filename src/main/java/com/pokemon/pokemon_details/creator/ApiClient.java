package com.pokemon.pokemon_details.creator;

import org.springframework.web.client.RestTemplate;

public class ApiClient {
    public static <T> T fetchList(String baseUrl, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(baseUrl, classType);
    }
}
