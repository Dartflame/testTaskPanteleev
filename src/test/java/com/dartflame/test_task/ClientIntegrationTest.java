package com.dartflame.test_task;

//import com.darflame.test_task.configs.WireMockConfig;
import com.dartflame.test_task.controllers.CurrencyController;
import com.dartflame.test_task.dao.CurrencyClient;
import org.bouncycastle.util.io.Streams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @Autowired
    private CurrencyClient client;

    @Autowired
    private CurrencyController controller;

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
    public void testRedirect()  {
        Assert.assertEquals(301, controller.readAllLatest(app_id, base, list, true, false).getStatusCodeValue());
    }

}
