package br.com.challengeibge.controller;

import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.json.StateJsonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class StateJsonController {
    private final StateJsonService stateJsonService;

    @GetMapping("/states")
    public ResponseEntity getStates(){
        List<StateResponse> states = stateJsonService.getListJson();
        return ResponseEntity.ok(states);

    }

}
