package br.com.challengeibge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("microrregiao")
    private MicroRegion microRegion;
}
