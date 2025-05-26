package com.pokemon.pokemon_details.unit;

import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import com.pokemon.pokemon_details.parameter.SortByParameter;
import com.pokemon.pokemon_details.parameter.SortDirectionParameter;
import com.pokemon.pokemon_details.repository.PokemonInformationRepository;
import com.pokemon.pokemon_details.service.PokemonInformationServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceTest {
    @Mock
    private PokemonInformationRepository pokemonInformationRepository;

    @InjectMocks
    private PokemonInformationServiceImp pokemonInformationService;

    private List<PokemonInformationDomain> listOfPokemon;

    @Before
    public void setup() {
        PokemonInformationDomain pokemon1 = new PokemonInformationDomain(UUID.randomUUID(), 1L, "bulbasaur", 97L, 360L, 23L);
        PokemonInformationDomain pokemon2 = new PokemonInformationDomain(UUID.randomUUID(), 2L, "squirtle", 345L, 26L, 57L);
        PokemonInformationDomain pokemon3 = new PokemonInformationDomain(UUID.randomUUID(), 3L, "pikachu", 34L, 55L, 90L);
        PokemonInformationDomain pokemon4 = new PokemonInformationDomain(UUID.randomUUID(), 4L, "charmander", 17L, 60L, 21L);
        PokemonInformationDomain pokemon5 = new PokemonInformationDomain(UUID.randomUUID(), 5L, "meowth", 90L, 617L, 52L);
        listOfPokemon = Arrays.asList(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5);
    }

    @Test
    public void getAllPokemonOrderedByAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.POKEMON_ID.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.ASC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.POKEMON_ID.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.DESC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.POKEMON_ID.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.ASC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.POKEMON_ID.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.DESC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByWeightAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.WEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.ASC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByWeightDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.WEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.DESC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByWeightAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.WEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.ASC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByWeightDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.WEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.DESC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByHeightAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.HEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.ASC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByHeightDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.HEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.DESC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByHeightAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.HEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.ASC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByHeightDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.HEIGHT.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.DESC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByBaseExperienceAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.BASE_EXPERIENCE.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.ASC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByBaseExperienceDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2000, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.BASE_EXPERIENCE.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon, PageRequest.of(0, 2000), listOfPokemon.size()));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.DESC, 0, 2000);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByBaseExperienceAsc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.ASC.getCode()), SortByParameter.BASE_EXPERIENCE.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.ASC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByBaseExperienceDesc() {
        given(pokemonInformationRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.fromString(SortDirectionParameter.DESC.getCode()), SortByParameter.BASE_EXPERIENCE.getCode()))))
                .willReturn(new PageImpl<>(listOfPokemon.subList(0, 2), PageRequest.of(0, 2), 2));
        var pokemonList = pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.DESC, 0, 2);
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isEqualTo(2);
    }

}
