package com.cafe24.exchange.common.properties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Component
@PropertySource("classpath:common.properties")
@ConfigurationProperties
public class CommonProperties {
    private String apiUrl;
    private String apiAccessKey;
    private String currencies;
    private int restTemplateReadTimeout;
    private int restTemplateConnectTimeout;
    private int httpClientMaxConnectTotal;
    private int httpClientMaxConnectPerRoute;
}
