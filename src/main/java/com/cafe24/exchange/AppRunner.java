package com.cafe24.exchange;

import com.cafe24.exchange.web.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class AppRunner implements ApplicationRunner{

    private final CurrencyService currencyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        currencyService.setCurrencyData();
    }
}
