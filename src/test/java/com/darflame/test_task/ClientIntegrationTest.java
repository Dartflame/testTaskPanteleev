package com.darflame.test_task;

//import com.darflame.test_task.configs.WireMockConfig;
import com.darflame.test_task.controllers.CurrencyController;
import com.darflame.test_task.dao.CurrencyClient;
import com.darflame.test_task.entity.Currency;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.bouncycastle.util.io.Streams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@ActiveProfiles("test")
//@EnableConfigurationProperties
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { WireMockConfig.class })
public class ClientIntegrationTest {

    private String app_id = "4d42f13d24494f7a930d519c059f8b92";
    private String base = "USD";
    private List<String> list;
    private String currency = "RUB";
    private String todayTestUrl = "https://openexchangerates.org/api/latest.json?app_id=4d42f13d24494f7a930d519c059f8b92&symbols=RUB";

    @Before
    public void addCurrencyToList (){
        list = new ArrayList<>();
        list.add(currency);
    }

//    @Autowired
//    private WireMockServer mockBooksService;

    @Autowired
    private CurrencyClient client;

    @Autowired
    private CurrencyController controller;

//    @BeforeEach
//    void setUp() throws IOException {
//        CurrencyMock.setupMockCurResponse(mockBooksService);
//    }
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("RUB");
        Assert.assertFalse(client.readAllCurrenciesLatest("4d42f13d24494f7a930d519c059f8b92", "USD", list, true, false) == null);
    }

    @Test
    public void testToday() throws IOException {
        addCurrencyToList();
        InputStream is = new URL(todayTestUrl).openStream();
        String jsonText = new String(Streams.readAll(is));

        Assert.assertEquals(jsonText, client.readAllCurrenciesLatest(app_id, base, list, true, false).toString());
    }

    @Test
    public void testYesterday() throws IOException {
        addCurrencyToList();
        LocalDate current = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterday = formatter.format(current);

        InputStream is = new URL("https://openexchangerates.org/api/historical/" + yesterday + ".json?app_id=4d42f13d24494f7a930d519c059f8b92&symbols=RUB").openStream();
        String jsonText = new String(Streams.readAll(is));

        Assert.assertEquals(jsonText, client.readAllCurrenciesHistorical(yesterday, app_id, base, list, true, false).toString());
    }

    @Test
    public void testRedirect() throws IOException {
        Assert.assertEquals(301, controller.readAllLatest(app_id, base, list, true, false).getStatusCodeValue());
    }

}
