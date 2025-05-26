package com.pokemon.pokemon_details.integration;

import com.pokemon.pokemon_details.creator.DataCreatorService;
import com.pokemon.pokemon_details.dto.PokemonInformationDTO;
import com.pokemon.pokemon_details.error.ErrorConstants;
import com.pokemon.pokemon_details.repository.PokemonInformationRepository;
import com.pokemon.pokemon_details.util.DataMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@SuppressWarnings("removal")
class PokemonInformationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataCreatorService dataCreateService;
    @Autowired
    private PokemonInformationRepository pokemonInformationRepository;


    @BeforeEach
    void setup() {
        PokemonInformationDTO pokemon1 = new PokemonInformationDTO(1L, "bulbasaur", 97L, 360L, 23L);
        PokemonInformationDTO pokemon2 = new PokemonInformationDTO(2L, "squirtle", 345L, 26L, 57L);
        PokemonInformationDTO pokemon3 = new PokemonInformationDTO(3L, "pikachu", 34L, 55L, 90L);
        PokemonInformationDTO pokemon4 = new PokemonInformationDTO(4L, "charmander", 17L, 60L, 21L);
        PokemonInformationDTO pokemon5 = new PokemonInformationDTO(5L, "meowth", 90L, 617L, 52L);
        pokemonInformationRepository.save(DataMapper.fromDto(pokemon1));
        pokemonInformationRepository.save(DataMapper.fromDto(pokemon2));
        pokemonInformationRepository.save(DataMapper.fromDto(pokemon3));
        pokemonInformationRepository.save(DataMapper.fromDto(pokemon4));
        pokemonInformationRepository.save(DataMapper.fromDto(pokemon5));
    }

    @AfterEach
    void teardown() {
        pokemonInformationRepository.deleteAll();
    }

    @Test
    public void whenGetPokemon_thenStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list").with(httpBasic("apiUser", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("bulbasaur"));

    }

    @Test
    public void whenGet3HeaviestPokemon_thenStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?sortBy=weight&page=0&size=3&sortDir=DESC").with(httpBasic("apiUser", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("meowth"));
    }

    @Test
    public void whenGet2TallestPokemon_thenStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?sortBy=height&page=0&size=2&sortDir=DESC").with(httpBasic("apiUser", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("pikachu"));
    }

    @Test
    public void whenGetExperiencedPokemon_thenStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?sortBy=baseExperience&page=0&size=1&sortDir=DESC").with(httpBasic("apiUser", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("squirtle"));
    }

    @Test
    public void whenUnauthorizedWithIncorrectCredentials_thenStatus401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list").with(httpBasic("apiUser", "wrongpassword")))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenUnauthorizedWithNoCredentials_thenStatus401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list"))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenBadRequestWithSortBy_thenStatus400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?sortBy=age").with(httpBasic("apiUser", "password")))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode", is(ErrorConstants.BAD_REQUEST_CODE)))
                .andExpect(jsonPath("$.error", is(ErrorConstants.BAD_REQUEST)))
                .andExpect(jsonPath("$.message", is("The accepted values for SortBy field are [weight, height, base experience, name, pokemonId]")));
    }

    @Test
    public void whenBadRequestWithSortDir_thenStatus400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?sortDir=DES").with(httpBasic("apiUser", "password")))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode", is(ErrorConstants.BAD_REQUEST_CODE)))
                .andExpect(jsonPath("$.error", is(ErrorConstants.BAD_REQUEST)))
                .andExpect(jsonPath("$.message", is("The accepted values for SortDirection field are [ASC, DESC]")));
        ;
    }

    @Test
    public void whenBadRequestWithPage_thenStatus400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?page=-1").with(httpBasic("apiUser", "password")))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode", is(ErrorConstants.BAD_REQUEST_CODE)))
                .andExpect(jsonPath("$.error", is(ErrorConstants.BAD_REQUEST)))
                .andExpect(jsonPath("$.message", is("Page index must not be less than zero")));
        ;
    }

    @Test
    public void whenBadRequestWithSize_thenStatus400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemon/list?size=0").with(httpBasic("apiUser", "password")))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode", is(ErrorConstants.BAD_REQUEST_CODE)))
                .andExpect(jsonPath("$.error", is(ErrorConstants.BAD_REQUEST)))
                .andExpect(jsonPath("$.message", is("Page size must not be less than one")));
        ;
    }

    @Test
    public void whenNotFound_thenStatus404() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pokemons/list").with(httpBasic("apiUser", "password")))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode", is(ErrorConstants.NOT_FOUND_CODE)))
                .andExpect(jsonPath("$.error", is(ErrorConstants.NOT_FOUND)))
                .andExpect(jsonPath("$.message", is("No static resource api/v1/pokemons/list.")));
        ;
    }

}