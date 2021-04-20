package br.com.challengeibge.service.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class GetCitiesServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private GetCitiesService getCitiesService;

    @Test
    public void test(){
        String cityString = "[" +
                "{" +
                "\"id\": 3500105" +
                "}" +
                "]";
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(new ResponseEntity<>(cityString, HttpStatus.OK));

        Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.any()))
                .thenReturn(new ArrayList<>());

        getCitiesService.getCitiesFromUF("SP");

    }

}