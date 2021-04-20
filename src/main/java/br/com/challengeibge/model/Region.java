package br.com.challengeibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Region implements Serializable {

    private Long id;

    @JsonProperty("sigla")
    private String initials;

    @JsonProperty("nome")
    private String name;
}
