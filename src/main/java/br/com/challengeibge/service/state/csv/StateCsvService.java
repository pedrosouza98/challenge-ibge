package br.com.challengeibge.service.state.csv;

import br.com.challengeibge.constants.CsvHeader;
import br.com.challengeibge.exception.CsvException;
import br.com.challengeibge.response.StateResponse;
import br.com.challengeibge.service.state.GetDataOfStateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
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
@Slf4j
public class StateCsvService {

    public final GetDataOfStateService getDataOfStateService;

    public InputStreamResource getListCsv(){
        log.info("init process of file csv");
        List<StateResponse> states = getDataOfStateService.getStatesWithCities();

        List<List<String>> csvBody = new ArrayList<>();

        states.forEach(stateResponse -> csvBody.add( getCsvBody(stateResponse) ));

        ByteArrayInputStream byteArrayOutputStream;

        log.info("csv will be mount with " + states.size() +  " lines");

        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter( new PrintWriter(out), CSVFormat.DEFAULT.withHeader(CsvHeader.title) )
        ) {
            for (List<String> record : csvBody) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
            log.info("csv was mounted with success");
        } catch (IOException e) {
            log.error("Cannot process file csv because: " + e.getMessage());
            throw new CsvException( e.getMessage() );
        }

        return new InputStreamResource(byteArrayOutputStream);
    }

    private List<String> getCsvBody(StateResponse stateResponse) {
        return Arrays.asList(
                stateResponse.getIdEstado().toString(),
                stateResponse.getSiglaEstado(),
                stateResponse.getRegiaoNome(),
                stateResponse.getNomeCidade(),
                stateResponse.getNomeMessoregiao(),
                stateResponse.getNomeFormatado()
        );
    }
}