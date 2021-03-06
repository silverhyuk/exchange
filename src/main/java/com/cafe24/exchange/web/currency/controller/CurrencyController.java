package com.cafe24.exchange.web.currency.controller;

import com.cafe24.exchange.common.properties.CommonProperties;
import com.cafe24.exchange.domain.currency.Currency;
import com.cafe24.exchange.web.currency.service.CurrencyService;
import com.cafe24.exchange.web.currency.vo.CurrencyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    /**
     * index page
     * @return ModelAndView
     */
    @GetMapping("")
    public ModelAndView index()throws Exception{
        log.info("index");

        ModelAndView mav = new ModelAndView();

        CurrencyVO currencyVO =  currencyService.getCurrencyList();


        mav.addObject("vo", currencyVO);
        mav.setViewName("currency/index");
        return mav;
    }
}
