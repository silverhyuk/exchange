package com.cafe24.exchange.common.config;

import com.cafe24.exchange.common.properties.CommonProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RestfulConfig {
    private final CommonProperties commonProperties;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(commonProperties.getRestTemplateReadTimeout());
        factory.setConnectTimeout(commonProperties.getRestTemplateConnectTimeout());

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(commonProperties.getHttpClientMaxConnectTotal())
                .setMaxConnPerRoute(commonProperties.getHttpClientMaxConnectPerRoute())
                .build();

        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }
}
