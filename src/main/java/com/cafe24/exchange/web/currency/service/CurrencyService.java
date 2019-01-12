package com.cafe24.exchange.web.currency.service;

import com.cafe24.exchange.common.properties.CommonProperties;
import com.cafe24.exchange.web.currency.repogitory.CurrencyRepository;
import com.cafe24.exchange.web.currency.vo.CurrencyVO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final CommonProperties commonProperties;
    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public boolean setCurrencyData(){

        boolean retVal = false;

        CurrencyVO currencyVO = getApi();

        if( currencyVO.isSuccess() == true ) {
            currencyRepository.setCurrencyVO(currencyVO);
            retVal = true;
        }
        return retVal;
    }

    public CurrencyVO getCurrencyData(){
        CurrencyVO currencyVO = Optional.ofNullable(currencyRepository.getCurrencyVO()).orElse(new CurrencyVO());

        return currencyVO;
    }

    private CurrencyVO getApi(){
        log.info("api url : {}", commonProperties.getApiUrl());
        log.info("api key : {}", commonProperties.getApiAccessKey());

        String apiUrl = commonProperties.getApiUrl() + "?access_key=" + commonProperties.getApiAccessKey();
        String jsonStringObj = restTemplate.getForObject(apiUrl, String.class);

        Gson gson = new Gson();
        CurrencyVO currencyVO = gson.fromJson(jsonStringObj, CurrencyVO.class);


        if(currencyVO.isSuccess() == false) {
            log.warn("Message : {}", jsonStringObj);
        }

        return currencyVO;
    }
}
