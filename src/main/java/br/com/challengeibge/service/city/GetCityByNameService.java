package br.com.challengeibge.service.city;

import br.com.challengeibge.exception.CityNotFoundException;
import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.GetDataOfStateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetCityByNameService {

    private final GetDataOfStateService getDataOfStateService;

    public Long getCityByName(String cityName){
        List<StateResponse> stateResponseList = getDataOfStateService.getStatesWithCities();

        for(StateResponse stateResponse: stateResponseList){
             if(cityName.equals( stateResponse.getNomeCidade()) ){
                 return stateResponse.getIdCidade();
             }
        }

        throw new CityNotFoundException(); //TODO: Tratar retorno de exceção
    }


}
