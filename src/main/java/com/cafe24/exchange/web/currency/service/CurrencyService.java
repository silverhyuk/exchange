package com.cafe24.exchange.web.currency.service;

import com.cafe24.exchange.common.properties.CommonProperties;
//import com.cafe24.exchange.web.currency.repogitory.CurrencyRepository;
import com.cafe24.exchange.domain.currency.Currency;
import com.cafe24.exchange.domain.currency.CurrencyRepository;
import com.cafe24.exchange.web.currency.vo.CurrencyVO;
import com.google.gson.Gson;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final CommonProperties commonProperties;
    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public boolean setCurrencyData(){

        boolean retVal = false;

        CurrencyVO vo = getApi();

        if( vo.isSuccess() ) {
            changeQuotes(vo);
            Map<String, Double> quotes =  vo.getQuotes();
            quotes.forEach((k, v)->{
                Currency currency = new Currency();
                currency.setNation(k);
                currency.setExchangeRate(v);
                currency.setSource(vo.getSource());
                currency.setRegDateTime(new Date());
                currency.setLastModifiedDateTime(new Date());
                Optional<Currency> optionalCurrency =  currencyRepository.findByNationAndSource(k, vo.getSource());
                optionalCurrency.orElse(currencyRepository.save(currency));
            });

            retVal = true;
        }
        return retVal;
    }

    public CurrencyVO getCurrencyList() throws Exception {
        List<Currency> currencyList = (List<Currency>) Optional.ofNullable(currencyRepository.findAll()).orElseThrow(Exception::new);
        CurrencyVO  vo = new CurrencyVO();
        for(Currency currency : currencyList){
            Map<String, Double> map = vo.getQuotes();
            map.put(currency.getNation(), currency.getExchangeRate());
        }
        vo.setSource(currencyList.get(0).getSource());
        vo.setSuccess(true);
        return vo;
    }

    public Currency getCurrencyData(String nation)throws Exception {
        Optional<Currency> optionalCurrency = currencyRepository.findByNation(nation);
        Currency currency = optionalCurrency.orElseThrow(Exception::new);
        return currency;
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

    private void changeQuotes(CurrencyVO currencyVO) {
        Map<String, Double> quotes = currencyVO.getQuotes();
        Map<String, Double> newQuotes = new HashMap<>();
        String source = currencyVO.getSource();
        quotes.forEach((k, v)->{
            if(k.length() == 6 && k.contains(source) == true) {
                String newKey = k.substring(3);
                newQuotes.put(newKey, v);
            }else{
                log.warn("Wrong quotes key=>value : {} => {}", k, v);
            }
        });
        currencyVO.setQuotes(newQuotes);
    }
}
