package br.com.challengeibge.service.city;

import br.com.challengeibge.exception.CityNotFoundException;
import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.GetDataOfStateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class GetCityByNameServiceTest {
    @InjectMocks
    private GetCityByNameService getCityByNameService;

    @Mock
    private GetDataOfStateService getDataOfStateService;

    @Test
    public void should_return_id_city_when_has_city_with_the_same_name(){
        StateResponse stateResponse = StateResponse.builder().nomeCidade("Jacareí").idCidade(12L).build();
        List<StateResponse> responseList = Collections.singletonList(stateResponse);

        Mockito.when(getDataOfStateService.getStatesWithCities()).thenReturn(responseList);


        Long idCity = getCityByNameService.getCityByName("Jacareí");

        Assert.assertEquals(12L, idCity.longValue());
    }

    @Test(expected = CityNotFoundException.class)
    public void should_return_exception_when_has_not_city_with_the_same_name(){
        StateResponse stateResponse = StateResponse.builder().nomeCidade("Jacareí").idCidade(12L).build();
        List<StateResponse> responseList = Collections.singletonList(stateResponse);

        Mockito.when(getDataOfStateService.getStatesWithCities()).thenReturn(responseList);


        getCityByNameService.getCityByName("Cidade_inexistente");
    }

    @Test(expected = CityNotFoundException.class)
    public void should_return_exception_when_the_list_is_empty(){
        List<StateResponse> responseList = new ArrayList<>();

        Mockito.when(getDataOfStateService.getStatesWithCities()).thenReturn(responseList);


        getCityByNameService.getCityByName("");
    }
}