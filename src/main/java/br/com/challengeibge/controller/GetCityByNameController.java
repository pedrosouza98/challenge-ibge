package br.com.challengeibge.controller;

import br.com.challengeibge.response.IdCityResponse;
import br.com.challengeibge.service.city.GetCityByNameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GetCityByNameController {

    private final GetCityByNameService getCityByNameService;

    @GetMapping(value = "/cities/name")
    public ResponseEntity<IdCityResponse> getCityByName(@RequestParam(value = "nomeCidade") String cityName){
        Long cityId = getCityByNameService.getCityByName(cityName);
        IdCityResponse idCityResponse = IdCityResponse.builder().idCidade(cityId).build();

        return ResponseEntity.ok(idCityResponse);
    }
}
