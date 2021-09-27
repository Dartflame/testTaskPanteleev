package com.darflame.test_task.controllers;

import com.darflame.test_task.services.CurrencyServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/gif")
public class GifController {

    @GetMapping
    public String gifPage(Model model) {
        model.addAttribute("gifURL", CurrencyServiceImpl.getGifURL());
        model.addAttribute("todayRate",CurrencyServiceImpl.getTodayRateView());
        model.addAttribute("yesterdayRate",CurrencyServiceImpl.getYesterdayRateView());
        model.addAttribute("todayDate",CurrencyServiceImpl.getTodayDate());
        model.addAttribute("yesterdayDate",CurrencyServiceImpl.getYesterdayDate());
        return "gif";
    }
}
