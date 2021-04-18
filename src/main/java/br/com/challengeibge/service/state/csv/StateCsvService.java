package br.com.challengeibge.service.state.csv;

import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.GetDataOfStateService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StateCsvService {

    public final GetDataOfStateService getDataOfStateService;

    public ResponseEntity getListCsv(){
        List<StateResponse> states = getDataOfStateService.getStatesWithCities();
        String[] csvHeader = {
                "idEstado", "siglaEstado", "regiaoNome", "nomeCidade", "nomeMesorregiao", "nomeFormatado"
        };
        List<List<String>> csvBody = new ArrayList<>();

        states.forEach(stateResponse -> {
            csvBody.add(Arrays.asList(
                    stateResponse.getIdEstado().toString(),
                    stateResponse.getSiglaEstado(),
                    stateResponse.getRegiaoNome(),
                    stateResponse.getNomeCidade(),
                    stateResponse.getNomeMessoregiao(),
                    stateResponse.getNomeFormatado()
            ));
        });

        ByteArrayInputStream byteArrayOutputStream;

        // closing resources by using a try with resources
        // https://www.baeldung.com/java-try-with-resources
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // defining the CSV printer
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        // withHeader is optional
                        CSVFormat.DEFAULT.withHeader(csvHeader)
                );
        ) {
            // populating the CSV content
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            // writing the underlying stream
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        String csvFileName = "people.csv";

        // setting HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );


    }
}
