package br.com.challengeibge.controller;

import br.com.challengeibge.response.IdCityResponse;
import br.com.challengeibge.service.city.GetCityByNameService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = GetCityByNameController.class)
public class GetCityByNameControllerTest {

    @MockBean
    private GetCityByNameService getCityByNameService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final static String URL = "/cities/name";

    @Test
    public void testGetCityByNameController() throws Exception {

        String cityName = "São Paulo";
        Long cityId = 8L;

        when(getCityByNameService.getCityByName(cityName)).thenReturn(cityId);

        IdCityResponse idCityResponseExpected = IdCityResponse.builder().idCidade(cityId).build();

        this.mockMvc.perform(get(URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .param("nomeCidade", cityName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(idCityResponseExpected)));
    }
}