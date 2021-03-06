package com.dartflame.test_task.services;

import com.dartflame.test_task.dao.CurrencyClient;
import com.dartflame.test_task.dao.GiphyClient;
import com.dartflame.test_task.entity.Currency;
import com.dartflame.test_task.entity.Gif;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Getter
    private static String gifURL;
    @Getter
    private static double todayRateView;
    @Getter
    private static double yesterdayRateView;
    @Getter
    private static String todayDate;
    @Getter
    private static String yesterdayDate;

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
        todayRateView = todayRate;

        LocalDate current = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterday = formatter.format(current);
        yesterdayDate = yesterday;
        todayDate = formatter.format(LocalDate.now());


        Currency currencyYesterday = currencyClient.readAllCurrenciesHistorical(yesterday, app_id, base, symbols, prettyprint, show_alternative);
        double yesterdayRate = currencyYesterday.getRates().get("RUB");
        yesterdayRateView = yesterdayRate;

        Gif gif = todayRate >= yesterdayRate? giphyClient.getGif("qvVDN4eyYwAtpzMK6xik3ovrQ5NesybZ","rich","","") :
                giphyClient.getGif("qvVDN4eyYwAtpzMK6xik3ovrQ5NesybZ","broke","","");


        HashMap<String,Object> images = (HashMap<String, Object>) gif.getData().get("images");
        HashMap<String,String> downsized_large = (HashMap<String, String>) images.get("downsized_large");
        String URL = downsized_large.get("url");

        gifURL = URL;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/gif"));

        return new ResponseEntity (gif, headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Override
    public ResponseEntity readAllHistorical(String date, String app_id, String base, List<String> symbols, boolean prettyprint, boolean show_alternative) {
        return ResponseEntity.ok(currencyClient.readAllCurrenciesHistorical(date, app_id, base, symbols, prettyprint, show_alternative));
    }

}
