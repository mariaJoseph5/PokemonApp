package com.pokemon.pokemon_details.mapper;

import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.util.DataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DataMapperTest {

    private PokemonInformationDTO pokemonInformationDTO;
    private PokemonInformationDomain pokemonInformationDomain;

    @BeforeEach
    void setup() {
        pokemonInformationDTO = new PokemonInformationDTO(1L, "bulbasaur", 97L, 360L, 23L);
        pokemonInformationDomain = new PokemonInformationDomain(UUID.randomUUID(), 1L, "bulbasaur", 97L, 360L, 23L);
    }

    @Test
    public void whenDtoIsInput_shouldOutputDomain() {
        PokemonInformationDomain pokemonInformationDomainObject = DataMapper.fromDto(pokemonInformationDTO);
        assertThat(pokemonInformationDomainObject.getPokemonId()).isEqualTo(pokemonInformationDTO.getPokemonId());
    }

    @Test
    public void whenDomainIsInput_shouldOutputDto() {
        PokemonInformationDTO pokemonInformationDTOObject = DataMapper.toDto(pokemonInformationDomain);
        assertThat(pokemonInformationDTOObject.getPokemonId()).isEqualTo(pokemonInformationDomain.getPokemonId());
    }


}
