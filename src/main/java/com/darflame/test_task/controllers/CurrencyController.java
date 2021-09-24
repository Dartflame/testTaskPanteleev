package com.darflame.test_task.controllers;

import com.darflame.test_task.dao.CurrencyClient;
import com.darflame.test_task.entity.Currency;
import com.darflame.test_task.services.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class CurrencyController {

    @Autowired
    private final CurrencyServiceImpl service;

    @Autowired
    private final CurrencyClient client;


//    @GetMapping("latest.json")
//    @ResponseBody
//    public ResponseEntity readAllLatest(
//            @RequestParam(required = true, defaultValue = "${app.feign.config.app_id}") String app_id,
//            @RequestParam(required = false, defaultValue = "${app.feign.config.currency_base}") String base,
//            @RequestParam(required = false, defaultValue = "${app.feign.config.currency}") List<String> symbols,
//            @RequestParam(required = false, defaultValue = "1") boolean prettyprint,
//            @RequestParam(required = false, defaultValue = "1") boolean show_alternative
//    ) {
//
//        return service.readAllLatest(app_id, base, symbols, prettyprint, show_alternative);
//    }

    @GetMapping("historical/{date}.json")
    @ResponseBody
    public ResponseEntity readAllHistorical(
            @PathVariable("date") String date,
            @RequestParam(required = true, defaultValue = "${app.feign.config.app_id}") String app_id,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency_base}") String base,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency}") List<String> symbols,
            @RequestParam(required = false, defaultValue = "1") boolean prettyprint,
            @RequestParam(required = false, defaultValue = "1") boolean show_alternative
    ) {

        return service.readAllHistorical(date, app_id, base, symbols, prettyprint, show_alternative);
    }

    @RequestMapping(value = "latest.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Currency> readAllLatest(
            @RequestParam(required = true, defaultValue = "${app.feign.config.app_id}") String app_id,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency_base}") String base,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency}") List<String> symbols,
            @RequestParam(required = false, defaultValue = "1") boolean prettyprint,
            @RequestParam(required = false, defaultValue = "1") boolean show_alternative
    ) {

        Currency cur = client.readAllCurrenciesLatest("4d42f13d24494f7a930d519c059f8b92","USD",symbols,true,false);
        return new ResponseEntity(cur,HttpStatus.OK);
    }

}

