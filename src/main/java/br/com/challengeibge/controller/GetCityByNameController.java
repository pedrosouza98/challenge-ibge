package br.com.challengeibge.controller;

import br.com.challengeibge.response.IdCityResponse;
import br.com.challengeibge.service.city.GetCityByNameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class GetCityByNameController {

    private final GetCityByNameService getCityByNameService;

    @GetMapping(value = "/cities/name")
    public ResponseEntity<IdCityResponse> getCityByName(@RequestParam(value = "nomeCidade") String cityName){
        log.info("Start process of search city by name");
        Long cityId = getCityByNameService.getCityByName(cityName);
        IdCityResponse idCityResponse = IdCityResponse.builder().idCidade(cityId).build();

        log.info("Finish process of search city by name");
        return ResponseEntity.ok(idCityResponse);
    }
}