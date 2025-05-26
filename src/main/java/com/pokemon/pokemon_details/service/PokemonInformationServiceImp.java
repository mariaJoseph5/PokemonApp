package com.pokemon.pokemon_details.service;

import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.parameter.SortByParameter;
import com.pokemon.pokemon_details.parameter.SortDirectionParameter;
import com.pokemon.pokemon_details.repository.PokemonInformationRepository;
import com.pokemon.pokemon_details.util.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonInformationServiceImp implements PokemonInformationService {

    @Autowired
    private PokemonInformationRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PokemonInformationService.class);

    @Override
    public void addPokemon(PokemonInformationDTO pokemon) {
        PokemonInformationDomain pokeInfo = DataMapper.fromDto(pokemon);
        repository.save(pokeInfo);
        logger.debug("Pokemon added: id={}, name={}, weight={}, height={}, base experience={}", pokemon.getPokemonId(), pokemon.getName(), pokemon.getWeight(), pokemon.getHeight(), pokemon.getBaseExperience());
    }

    @Override
    public List<PokemonInformationDTO> fetchPokemonList(SortByParameter sortBy, SortDirectionParameter sortDir, int page, int size) {
        List<PokemonInformationDomain> items;
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir.toString()), sortBy.getCode());
        Pageable pageable = PageRequest.of(page, size, sort);
        items = repository.findAll(pageable).stream().toList();
        return items.stream().map(DataMapper::toDto).collect(Collectors.toList());
    }

}
