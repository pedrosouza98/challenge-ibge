package br.com.challengeibge.service.state;

import br.com.challengeibge.client.CityClient;
import br.com.challengeibge.client.StateClient;
import br.com.challengeibge.model.City;
import br.com.challengeibge.model.State;
import br.com.challengeibge.response.StateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GetDataOfStateService {
    private final CityClient cityClient;
    private final StateClient getStateClient;

    public List<StateResponse> getStatesWithCities(){
        log.info("Will find states with cities");
        List<StateResponse> responses = new ArrayList<>();

        List<State> stateList = getStateClient.getAllState();

        log.info("Found " + stateList.size() + " states");

        stateList.forEach(state -> {
            log.info("Will search cities of state = " + state.getInitials());
            List<City> cityList = cityClient.getCitiesFromUF(state.getInitials());
            log.info("Found " + cityList.size() + " cities of state = " + state.getInitials());

            cityList.forEach(city -> {
                StateResponse stateResponse = getStateResponse(state, city);
                responses.add(stateResponse);
            });
        });

        log.info("Will be returned " + responses.size() + " StatesResponse");
        return responses;
    }

    private StateResponse getStateResponse(State state, City city) {
        return StateResponse.builder()
                .idEstado(state.getId())
                .siglaEstado(state.getInitials())
                .regiaoNome(state.getRegion().getName())
                .nomeCidade(city.getName())
                .nomeMessoregiao(city.getMicroRegion().getMesoRegion().getName())
                .nomeFormatado(city.getName() + "/" + state.getInitials())
                .idCidade(city.getId())
                .build();
    }
}