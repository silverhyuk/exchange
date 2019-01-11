package com.cafe24.exchange.web.currency.controller;

import com.cafe24.exchange.common.properties.CommonProperties;
import com.cafe24.exchange.web.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CommonProperties commonProperties;
    private final RestTemplate restTemplate;
    /**
     * index page
     * @return ModelAndView
     */
    @GetMapping("/currency")
    public ModelAndView index(){
        log.info("index");
        log.info("api url : {}", commonProperties.getApiUrl());
        log.info("api key : {}", commonProperties.getApiAccessKey());
        String apiUrl = commonProperties.getApiUrl() + "?access_key=" + commonProperties.getApiAccessKey();
        String Obj = restTemplate.getForObject(apiUrl, String.class);

        log.info(Obj);
        ModelAndView mav = new ModelAndView();

        mav.setViewName("currency/index");
        return mav;
    }
}
