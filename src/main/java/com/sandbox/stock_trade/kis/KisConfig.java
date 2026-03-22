package com.sandbox.stock_trade.kis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kis")
public class KisConfig {
    private String appKey;
    private String appSecret;
    private String baseUrl;
}