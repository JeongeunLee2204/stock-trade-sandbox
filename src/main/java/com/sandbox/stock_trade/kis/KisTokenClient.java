package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import jakarta.annotation.PostConstruct;



@Component
@RequiredArgsConstructor
public class KisTokenClient {

    @PostConstruct
    public void init() {
        fetchAccessToken(); // 앱 시작할 때 토큰 미리 발급
    }

    private final KisConfig kisConfig;
    private String cachedToken = null;
    private final Object lock = new Object();

    public String fetchAccessToken() {
        synchronized (lock) {
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
}