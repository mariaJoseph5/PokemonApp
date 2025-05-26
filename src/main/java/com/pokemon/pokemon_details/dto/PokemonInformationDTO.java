package com.pokemon.pokemon_details.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonInformationDTO {

    @JsonProperty("id")
    private Long pokemonId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("base_experience")
    private Long baseExperience;
    @JsonProperty("weight")
    private Long weight;
    @JsonProperty("height")
    private Long height;
}
