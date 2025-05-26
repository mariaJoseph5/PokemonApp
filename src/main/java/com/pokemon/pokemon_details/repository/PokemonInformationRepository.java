package com.pokemon.pokemon_details.repository;

import com.pokemon.pokemon_details.domain.PokemonInformationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PokemonInformationRepository extends JpaRepository<PokemonInformationDomain, UUID> {
}
