package com.cafe24.exchange.web.currency.controller;

import com.cafe24.exchange.common.properties.CommonProperties;
import com.cafe24.exchange.web.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CommonProperties commonProperties;
    /**
     * index page
     * @return ModelAndView
     */
    @GetMapping("/currency")
    public ModelAndView index(){
        log.info("index");
        log.info("api url : {}", commonProperties.getApiUrl());
        log.info("api key : {}", commonProperties.getApiAccessKey());
        ModelAndView mav = new ModelAndView();

        mav.setViewName("currency/index");
        return mav;
    }
}
