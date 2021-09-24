package com.darflame.test_task.controllers;

import com.darflame.test_task.services.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;



@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class CurrencyController {

    @Autowired
    private final CurrencyServiceImpl service;

    @GetMapping("latest.json")
    @ResponseBody
    public ResponseEntity readAllLatest(
            @RequestParam(required = true, defaultValue = "${app.feign.config.app_id}") String app_id,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency_base}") String base,
            @RequestParam(required = false, defaultValue = "${app.feign.config.currency}") List<String> symbols,
            @RequestParam(required = false, defaultValue = "1") boolean prettyprint,
            @RequestParam(required = false, defaultValue = "1") boolean show_alternative
    ) {

        return service.readAllLatest(app_id, base, symbols, prettyprint, show_alternative);
    }

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
}

