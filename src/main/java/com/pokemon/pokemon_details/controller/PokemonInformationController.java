package com.pokemon.pokemon_details.controller;

import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.parameter.SortByParameter;
import com.pokemon.pokemon_details.parameter.SortDirectionParameter;
import com.pokemon.pokemon_details.service.PokemonInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonInformationController {

    @Autowired
    private PokemonInformationService pokemonInformationService;

    @GetMapping("/list")
    public ResponseEntity<List<PokemonInformationDTO>> fetchPokemonInformation(@RequestParam(required = false, defaultValue = "pokemonId") String sortBy, @RequestParam(required = false, defaultValue = "ASC") String sortDir, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "2000") int size) {
        SortByParameter sortByParameter = SortByParameter.fromCode(sortBy);
        SortDirectionParameter sortDirectionParameter = SortDirectionParameter.fromCode(sortDir);
        return new ResponseEntity<>(pokemonInformationService.fetchPokemonList(sortByParameter, sortDirectionParameter, page, size), HttpStatus.OK);
    }
}
