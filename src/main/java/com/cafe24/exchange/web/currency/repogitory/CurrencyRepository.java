package com.cafe24.exchange.web.currency.repogitory;

import com.cafe24.exchange.web.currency.vo.CurrencyVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Repository
public class CurrencyRepository {
    private CurrencyVO currencyVO;
}
