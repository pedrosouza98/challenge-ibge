package br.com.challengeibge.service;

import br.com.challengeibge.model.City;
import br.com.challengeibge.model.State;
import br.com.challengeibge.response.StateResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetDataOfStateService {
    private final GetCitiesService getCitiesService;
    private final GetStateService getStateService;

    public List<StateResponse> getStates(){
        List<StateResponse> responses = new ArrayList<>();

        // Pegar todos os estados.
        List<State> stateList = getStateService.getAllState();

        // Percorrer todos os estados.
        stateList.forEach(state -> {
            // Chamar todos os municipios daquele estado.
            List<City> cityList = getCitiesService.getCitiesFromUF(state.getInitials());
            cityList.forEach(city -> {
                // Então montar objeto de retorno.
                StateResponse stateResponse = StateResponse.builder()
                        .idEstado(state.getId())
                        .siglaEstado(state.getInitials())
                        .regiaoNome(state.getRegion().getName())
                        .nomeCidade(city.getName())
                        .nomeMessoregiao(city.getMicroRegion().getMesoRegion().getName())
                        .nomeFormatado(city.getName() + "/" + state.getInitials())
                        .build();

                responses.add(stateResponse);

            });
        });
        return responses;
    }
}
