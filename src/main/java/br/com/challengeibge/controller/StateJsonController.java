package br.com.challengeibge.controller;

import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.json.StateJsonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class StateJsonController {
    private final StateJsonService stateJsonService;

    @GetMapping("/json/states")
    public ResponseEntity getStates(){
        log.info("Will start process of json states");
        List<StateResponse> states = stateJsonService.getListJson();
        log.info("Finish process of json states");
        return ResponseEntity.ok(states);

    }

}