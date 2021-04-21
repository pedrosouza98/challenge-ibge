package br.com.challengeibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class State implements Serializable {
    private Long id;

    @JsonProperty("sigla")
    private String initials;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("regiao")
    private Region region;
}
