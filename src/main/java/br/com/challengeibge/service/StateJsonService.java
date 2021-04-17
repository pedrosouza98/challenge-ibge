package br.com.challengeibge.service;

import br.com.challengeibge.response.StateResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StateJsonService {

    public final GetDataOfStateService getDataOfStateService;

    public List<StateResponse> getListJson(){
        return getDataOfStateService.getStates();
    }
}
