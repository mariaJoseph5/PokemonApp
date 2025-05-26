package com.pokemon.pokemon_details.util;

import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;


public class DataMapper {

    public static PokemonInformationDTO toDto(PokemonInformationDomain pokemonInformation) {
        PokemonInformationDTO dto = new PokemonInformationDTO();
        dto.setPokemonId(pokemonInformation.getPokemonId());
        dto.setName(pokemonInformation.getName());
        dto.setBaseExperience(pokemonInformation.getBaseExperience());
        dto.setHeight(pokemonInformation.getHeight());
        dto.setWeight(pokemonInformation.getWeight());

        return dto;
    }

    public static PokemonInformationDomain fromDto(PokemonInformationDTO pokemonInformationDTO) {
        PokemonInformationDomain pokemon = new PokemonInformationDomain();
        pokemon.setName(pokemonInformationDTO.getName());
        pokemon.setPokemonId(pokemonInformationDTO.getPokemonId());
        pokemon.setHeight(pokemonInformationDTO.getHeight());
        pokemon.setWeight(pokemonInformationDTO.getWeight());
        pokemon.setBaseExperience(pokemonInformationDTO.getBaseExperience());
        return pokemon;
    }
}
