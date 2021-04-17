package br.com.challengeibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class State {
    private Long id;

    @JsonProperty("sigla")
    private String initials;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("regiao")
    private Region region;
}
