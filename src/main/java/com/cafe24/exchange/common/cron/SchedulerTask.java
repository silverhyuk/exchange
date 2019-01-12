package com.cafe24.exchange.common.cron;


import com.cafe24.exchange.web.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class SchedulerTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final CurrencyService currencyService;
    /**
     * Every Hour Croned
     */
    @Scheduled(cron = "0 5 * * * *")
    public void runEveryHour() {
        currencyService.setCurrencyData();
        log.info("Cron expression - Run every hour - " + dateFormat.format(new Date()));
    }
}
