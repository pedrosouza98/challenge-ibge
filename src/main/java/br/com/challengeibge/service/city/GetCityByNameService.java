package br.com.challengeibge.service.city;

import br.com.challengeibge.exception.CityNotFoundException;
import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.GetDataOfStateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
@Slf4j

public class GetCityByNameService {
    private final GetDataOfStateService getDataOfStateService;

    @Cacheable(value = "cities", key = "#cityName")
    public Long getCityByName(String cityName){

        List<StateResponse> stateResponseList = getDataOfStateService.getStatesWithCities();
        log.info("Will be search city = " + cityName);
        for(StateResponse stateResponse: stateResponseList){
            if(cityName.equals( stateResponse.getNomeCidade()) ){
                log.info("Found city = " + cityName );
                return stateResponse.getIdCidade();
            }
        }

        log.error("Not found city = " + cityName);
        throw new CityNotFoundException();
    }


}
