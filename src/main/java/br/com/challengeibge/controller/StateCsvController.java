package br.com.challengeibge.controller;

import br.com.challengeibge.service.state.csv.StateCsvService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StateCsvController {
    private final StateCsvService stateCsvService;

    @GetMapping(value = "/csv/states", produces = "text/csv")
    public ResponseEntity<InputStreamResource> getStates(){
        InputStreamResource fileInputStream = stateCsvService.getListCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "state.csv");
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }
}