package br.com.challengeibge.service.state;

import br.com.challengeibge.client.StateClient;
import br.com.challengeibge.model.*;
import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.client.CityClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GetDataOfStateServiceTest {

    @InjectMocks
    private GetDataOfStateService getDataOfStateService;

    @Mock
    private CityClient cityClient;

    @Mock
    private StateClient getStateClient;

    @Test
    public void should_return_empty_list_when_has_not_states(){
        //Mock
        Mockito.when(getStateClient.getAllState())
                .thenReturn(new ArrayList<>());

        //Method call
        List<StateResponse> statesWithCitiesList = getDataOfStateService.getStatesWithCities();

        //Assertions
        Assert.assertEquals(0, statesWithCitiesList.size());
    }

    @Test
    public void should_return_empty_list_when_has_not_cities(){
        //Mock
        State stateSP = State.builder().initials("SP").build();

        Mockito.when(getStateClient.getAllState())
                .thenReturn(Collections.singletonList(stateSP));

        Mockito.when(cityClient.getCitiesFromUF(stateSP.getInitials()))
                .thenReturn(new ArrayList<>());

        //Method call
        List<StateResponse> statesWithCitiesList = getDataOfStateService.getStatesWithCities();

        //Assertions
        Assert.assertEquals(0, statesWithCitiesList.size());
    }

    @Test
    public void should_return_stateResponse_when_has_state_and_city(){
        //Mock
        MesoRegion mesoRegion = MesoRegion.builder().build();
        MicroRegion microRegion = MicroRegion.builder().mesoRegion(mesoRegion).build();
        City city = City.builder().microRegion(microRegion).build();

        Region region = Region.builder().build();
        State stateSP = State.builder().region(region).initials("SP").build();

        Mockito.when(getStateClient.getAllState())
                .thenReturn(Collections.singletonList(stateSP));

        Mockito.when(cityClient.getCitiesFromUF(stateSP.getInitials()))
                .thenReturn(Collections.singletonList(city));

        //Method call
        List<StateResponse> statesWithCitiesList = getDataOfStateService.getStatesWithCities();

        //Assertions
        Assert.assertEquals(1, statesWithCitiesList.size());
    }
}