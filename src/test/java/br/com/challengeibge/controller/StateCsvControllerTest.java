package br.com.challengeibge.controller;

import br.com.challengeibge.service.state.csv.StateCsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StateCsvController.class)
public class StateCsvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StateCsvService stateCsvService;

    @Test
    public void test_State_Csv_Controller() throws Exception {
        this.mockMvc.perform(get("/csv/states")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}