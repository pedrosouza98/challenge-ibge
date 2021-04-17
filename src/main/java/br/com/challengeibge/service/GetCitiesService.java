package br.com.challengeibge.service;

import br.com.challengeibge.model.City;
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
public class GetCitiesService {
    private final String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";

    public List<City> getCitiesFromUF(String uf){
        String completeUrl = url + uf + "/municipios";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
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
