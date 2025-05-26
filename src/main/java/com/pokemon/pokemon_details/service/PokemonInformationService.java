package com.pokemon.pokemon_details.service;

import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.parameter.SortByParameter;
import com.pokemon.pokemon_details.parameter.SortDirectionParameter;

import java.util.List;

public interface PokemonInformationService {
    void addPokemon(PokemonInformationDTO pokemon);

    List<PokemonInformationDTO> fetchPokemonList(SortByParameter sortBy, SortDirectionParameter sortDir, int page, int size);
}
