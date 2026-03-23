package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KisTokenClient {

    private final KisConfig kisConfig;
    private String cachedToken = null;

    public String fetchAccessToken() {
        if (cachedToken != null) {
            return cachedToken;
        }

        WebClient client = WebClient.create(kisConfig.getBaseUrl());

        KisTokenResponse response = client.post()
                .uri("/oauth2/tokenP")
                .header("Content-Type", "application/json")
                .bodyValue(Map.of(
                        "grant_type", "client_credentials",
                        "appkey", kisConfig.getAppKey(),
                        "appsecret", kisConfig.getAppSecret()
                ))
                .retrieve()
                .bodyToMono(KisTokenResponse.class)
                .block();

        cachedToken = response.getAccessToken();
        return cachedToken;
    }
}