package br.com.challengeibge.controller;

import br.com.challengeibge.service.state.csv.StateCsvService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StateCsvController {
    private final StateCsvService stateCsvService;

    @GetMapping(value = "/csv/states", produces = "text/csv")
    public ResponseEntity getStates(){
        return stateCsvService.getListCsv();
    }
}
