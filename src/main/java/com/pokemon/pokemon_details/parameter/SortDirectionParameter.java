package com.pokemon.pokemon_details.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SortDirectionParameter {
    ASC("ASC"), DESC("DESC");

    private final String code;

    SortDirectionParameter(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static SortDirectionParameter fromCode(String value) {
        for (SortDirectionParameter s : values()) {
            if (s.code.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("The accepted values for SortDirection field are [ASC, DESC]");
    }
}
