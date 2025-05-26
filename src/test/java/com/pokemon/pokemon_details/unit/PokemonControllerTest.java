package com.pokemon.pokemon_details.unit;

import com.pokemon.pokemon_details.controller.PokemonInformationController;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.parameter.SortByParameter;
import com.pokemon.pokemon_details.parameter.SortDirectionParameter;
import com.pokemon.pokemon_details.service.PokemonInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {
    @Mock
    private PokemonInformationService pokemonInformationService;

    @InjectMocks
    private PokemonInformationController pokemonInformationController;

    private List<PokemonInformationDTO> listOfPokemon;

    @Before
    public void setup() {
        PokemonInformationDTO pokemon1 = new PokemonInformationDTO(1L, "bulbasaur", 97L, 360L, 23L);
        PokemonInformationDTO pokemon2 = new PokemonInformationDTO(2L, "squirtle", 345L, 26L, 57L);
        PokemonInformationDTO pokemon3 = new PokemonInformationDTO(3L, "pikachu", 34L, 55L, 90L);
        PokemonInformationDTO pokemon4 = new PokemonInformationDTO(4L, "charmander", 17L, 60L, 21L);
        PokemonInformationDTO pokemon5 = new PokemonInformationDTO(5L, "meowth", 90L, 617L, 52L);
        listOfPokemon = Arrays.asList(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5);
    }

    @Test
    public void getAllPokemonOrderedByAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.ASC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.POKEMON_ID.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.DESC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.POKEMON_ID.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.ASC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.POKEMON_ID.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.POKEMON_ID, SortDirectionParameter.DESC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.POKEMON_ID.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByWeightAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.ASC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.WEIGHT.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByWeightDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.DESC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.WEIGHT.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByWeightAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.ASC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.WEIGHT.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByWeightDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.WEIGHT, SortDirectionParameter.DESC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.WEIGHT.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByHeightAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.ASC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.HEIGHT.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByHeightDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.DESC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.HEIGHT.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByHeightAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.ASC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.HEIGHT.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByHeightDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.HEIGHT, SortDirectionParameter.DESC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.HEIGHT.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getAllPokemonOrderedByBaseExperienceAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.ASC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.BASE_EXPERIENCE.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getAllPokemonOrderedByBaseExperienceDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.DESC, 0, 2000))
                .willReturn(listOfPokemon);
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.BASE_EXPERIENCE.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2000);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getFirstTwoPokemonOrderedByBaseExperienceAsc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.ASC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.BASE_EXPERIENCE.getCode(), SortDirectionParameter.ASC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getFirstTwoPokemonOrderedByBaseExperienceDesc() {
        given(pokemonInformationService.fetchPokemonList(SortByParameter.BASE_EXPERIENCE, SortDirectionParameter.DESC, 0, 2))
                .willReturn(listOfPokemon.subList(0, 2));
        var responseEntity = pokemonInformationController.fetchPokemonInformation(SortByParameter.BASE_EXPERIENCE.getCode(), SortDirectionParameter.DESC.getCode(), 0, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }
}
