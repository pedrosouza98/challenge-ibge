package br.com.challengeibge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("microrregiao")
    private MicroRegion microRegion;
}
