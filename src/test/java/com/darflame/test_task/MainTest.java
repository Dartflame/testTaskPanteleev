package com.darflame.test_task;


import com.darflame.test_task.controllers.CurrencyController;
import com.darflame.test_task.dao.CurrencyClient;
import com.darflame.test_task.entity.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.util.io.Streams;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyClient client;

    @Autowired
    private CurrencyController controller;

    @Test
    public void testing() throws Exception {
        //this.mockMvc.perform(get("/latest.json")).andDo(print()).andExpect(status().is3xxRedirection());
        InputStream is = new URL("https://openexchangerates.org/api/latest.json?app_id=4d42f13d24494f7a930d519c059f8b92&symbols=RUB").openStream();
        //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = new String(Streams.readAll(is));
        //ResultActions resultActions = this.mockMvc.perform(get("https://openexchangerates.org/api/latest.json?app_id=4d42f13d24494f7a930d519c059f8b92&symbols=RUB"));
        Assert.assertEquals(jsonText, "{\n" +
                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                "  \"timestamp\": 1632495599,\n" +
                "  \"base\": \"USD\",\n" +
                "  \"rates\": {\n" +
                "    \"RUB\": 72.9315\n" +
                "  }\n" +
                "}");
    }

    @Test
    public void mockTest() throws Exception {
       // ResultActions resultActions = mockMvc.perform(get("http://localhost:8080/latest.json")).andExpect(status().isOk());

    }

}
