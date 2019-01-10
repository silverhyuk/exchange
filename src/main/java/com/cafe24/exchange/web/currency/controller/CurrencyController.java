package com.cafe24.exchange.web.currency.controller;

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

    /**
     * index page
     * @return ModelAndView
     */
    @GetMapping("/index")
    public ModelAndView index(){
        log.info("index");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("currency/index");
        return mav;
    }
}
