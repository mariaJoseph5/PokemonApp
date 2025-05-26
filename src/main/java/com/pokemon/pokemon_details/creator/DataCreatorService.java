package com.pokemon.pokemon_details.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.service.PokemonInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataCreatorService {

    @Autowired
    private PokemonInformationService pokemonInformationService;
    @Value("${pokemon.base.endpoint}")
    private String baseUrl;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void runAfterStartup() {
        final Logger logger = LoggerFactory.getLogger(DataCreatorService.class);

        String response = ApiClient.fetchList(baseUrl, String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultsNode = rootNode.get("results");
            int count = rootNode.get("count").asInt();
            int savedRecords = 0;
            if (resultsNode.isArray()) {
                for (JsonNode result : resultsNode) {
                    String urlValue = result.get("url").asText();
                    PokemonInformationDTO responsePokemonData = ApiClient.fetchList(urlValue, PokemonInformationDTO.class);
                    pokemonInformationService.addPokemon(responsePokemonData);
                    savedRecords++;
                    logger.info("Saved {}/{} pokemon", savedRecords, count);
                }
            }
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Error in parsing JSON");
        }
    }
}
