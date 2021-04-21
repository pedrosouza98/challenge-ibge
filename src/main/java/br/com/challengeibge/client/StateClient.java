package br.com.challengeibge.client;

import br.com.challengeibge.model.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StateClient {
    private final String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public List<State> getAllState() {
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