package br.com.challengeibge.service;

import br.com.challengeibge.model.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetStateService {
    private final String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

    public List<State> getAllState() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        List<State> states = new ArrayList<>();
        try {
            states = mapper.readValue(response.getBody(), new TypeReference<List<State>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return states;
    }
}
