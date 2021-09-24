package com.darflame.test_task.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Currency {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    Map<String,Double> rates = new HashMap<>();

    @Override
    public String toString() {
        return  "{\n" +
                "  \"disclaimer\": \""+disclaimer+"\",\n" +
                "  \"license\": \""+license+"\",\n" +
                "  \"timestamp\": "+timestamp+",\n" +
                "  \"base\": \""+base+"\",\n" +
                "  \"rates\": {\n" +
                "    \"RUB\": "+rates.get("RUB")+"\n" +
                "  }\n" +
                "}";
    }
}
