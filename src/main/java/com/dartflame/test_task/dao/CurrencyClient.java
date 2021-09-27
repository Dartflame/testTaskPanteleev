package com.dartflame.test_task.dao;

import com.dartflame.test_task.entity.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${app.feign.config.name2}", url = "${app.feign.config.url2}")
public interface CurrencyClient {

        @RequestMapping(method = RequestMethod.GET, value = "/latest.json")
        Currency readAllCurrenciesLatest(@RequestParam String app_id,
                                         @RequestParam String base,
                                         @RequestParam List<String> symbols,
                                         @RequestParam boolean prettyprint,
                                         @RequestParam boolean show_alternative);

        @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json")
        Currency readAllCurrenciesHistorical(@PathVariable("date") String date,
                                             @RequestParam String app_id,
                                             @RequestParam String base,
                                             @RequestParam List<String> symbols,
                                             @RequestParam boolean prettyprint,
                                             @RequestParam boolean show_alternative);

}


