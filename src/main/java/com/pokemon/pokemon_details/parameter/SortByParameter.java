package com.pokemon.pokemon_details.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SortByParameter {
    WEIGHT("weight"), HEIGHT("height"), BASE_EXPERIENCE("baseExperience"), NAME("name"), POKEMON_ID("pokemonId");
    private final String code;

    SortByParameter(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static SortByParameter fromCode(String value) {
        for (SortByParameter s : values()) {
            if (s.code.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("The accepted values for SortBy field are [weight, height, base experience, name, pokemonId]");
    }

}
