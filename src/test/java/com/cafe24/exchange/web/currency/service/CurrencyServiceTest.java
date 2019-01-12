package com.cafe24.exchange.web.currency.service;

import com.cafe24.exchange.web.currency.vo.CurrencyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    private  CurrencyService currencyService;

    @Test
    public void setCurrencyData() {
        assertThat(currencyService.setCurrencyData()).isEqualTo(true);
    }

    @Test
    public void getCurrencyData() {
        CurrencyVO currencyVO = currencyService.getCurrencyData();
        assertThat(currencyVO).isNotNull();
        assertThat(currencyVO.isSuccess()).isEqualTo(true);
        assertThat(currencyVO.getQuotes().size()).isGreaterThan(0);

    }
}