package com.pokemon.pokemon_details.creater;

import com.pokemon.pokemon_details.creator.DataCreatorService;
import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.repository.PokemonInformationRepository;
import com.pokemon.pokemon_details.service.PokemonInformationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings("removal")
public class DataCreatorServiceTest {

    @InjectMocks
    private DataCreatorService dataCreatorService;

    @MockBean
    private PokemonInformationRepository pokemonInformationRepository;

    @SpyBean
    private PokemonInformationServiceImp pokemonInformationService;

    @Test
    public void whenApplicationStarts_addDataToRepo() {
        given(pokemonInformationRepository.save(any(PokemonInformationDomain.class))).willReturn(null);
        verify(pokemonInformationService, times(5)).addPokemon(any(PokemonInformationDTO.class));
    }
}
