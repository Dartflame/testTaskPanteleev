package com.darflame.test_task.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CurrencyService {

    ResponseEntity readAllHistorical(String date, String app_id, String base, List<String> symbols, boolean prettyprint, boolean show_alternative);

    ResponseEntity readAllLatest(String app_id, String base, List<String> symbols, boolean prettyprint, boolean show_alternative);

}
