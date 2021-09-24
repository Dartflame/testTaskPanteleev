package com.darflame.test_task.services;

import com.darflame.test_task.dao.CurrencyClient;
import com.darflame.test_task.dao.GiphyClient;
import com.darflame.test_task.entity.Currency;
import com.darflame.test_task.entity.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyClient currencyClient;

    @Autowired
    private GiphyClient giphyClient;

    @Autowired
    public void setCurrencyClient(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Autowired
    public void setGiphyClient(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public ResponseEntity readAllLatest(String app_id, String base, List<String> symbols, boolean prettyprint, boolean show_alternative) {
        Currency currencyToday = currencyClient.readAllCurrenciesLatest(app_id, base, symbols,prettyprint,show_alternative);
        double todayRate = currencyToday.getRates().get("RUB");
        System.out.println(todayRate);

        LocalDate current = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterday = formatter.format(current);
        System.out.println(yesterday);

        Currency currencyYesterday = currencyClient.readAllCurrenciesHistorical(yesterday, app_id, base, symbols, prettyprint, show_alternative);
        double yesterdayRate = currencyYesterday.getRates().get("RUB");
        System.out.println(yesterdayRate);

        Gif gif = todayRate >= yesterdayRate? giphyClient.getGif("qvVDN4eyYwAtpzMK6xik3ovrQ5NesybZ","rich","","") :
                giphyClient.getGif("qvVDN4eyYwAtpzMK6xik3ovrQ5NesybZ","broke","","");

        String url = (String) gif.getData().get("embed_url");
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Override
    public ResponseEntity readAllHistorical(String date, String app_id, String base, List<String> symbols, boolean prettyprint, boolean show_alternative) {
        return ResponseEntity.ok(currencyClient.readAllCurrenciesHistorical(date, app_id, base, symbols, prettyprint, show_alternative));
    }


}
