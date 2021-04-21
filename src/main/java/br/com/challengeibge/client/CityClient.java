package br.com.challengeibge.client;

import br.com.challengeibge.model.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class CityClient {
    private final String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public List<City> getCitiesFromUF(String uf){
        String completeUrl = url + uf + "/municipios";
        ResponseEntity<String> response = restTemplate.getForEntity(completeUrl, String.class);

        List<City> cities = new ArrayList<>();
        try {
            cities = mapper.readValue(response.getBody(), new TypeReference<List<City>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return cities;
    }
}