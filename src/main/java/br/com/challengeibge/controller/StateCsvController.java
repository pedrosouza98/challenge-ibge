package br.com.challengeibge.controller;

import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.StateCsvService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class StateCsvController {
    private final StateCsvService stateCsvService;

    @GetMapping(value = "/csv/states", produces = "text/csv")
    public ResponseEntity getStates(){
        return stateCsvService.getListCsv();
    }
}
