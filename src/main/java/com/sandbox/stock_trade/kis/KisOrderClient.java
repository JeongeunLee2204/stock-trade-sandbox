package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KisOrderClient {

    private final KisConfig kisConfig;
    private final KisTokenClient kisTokenClient;

    public KisOrderResponse executeOrder(KisOrderRequest request, String trId) {
        String token = kisTokenClient.fetchAccessToken();
        WebClient client = WebClient.create(kisConfig.getBaseUrl());

        return client.post()
                .uri("/uapi/domestic-stock/v1/trading/order-cash")
                .header("Authorization", "Bearer " + token)
                .header("appkey", kisConfig.getAppKey())
                .header("appsecret", kisConfig.getAppSecret())
                .header("tr_id", trId)
                .header("custtype", "P")
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(KisOrderResponse.class)
                .block();
    }

    public KisOrderResponse buy(String accountNo, String productCode, String stockCode, String qty, String price) {
        KisOrderRequest request = new KisOrderRequest(
                accountNo, productCode, stockCode, "01", qty, price
        );
        return executeOrder(request, "VTTC0802U"); // 모의투자 매수 tr_id
    }

    public KisOrderResponse sell(String accountNo, String productCode, String stockCode, String qty, String price) {
        KisOrderRequest request = new KisOrderRequest(
                accountNo, productCode, stockCode, "01", qty, price
        );
        return executeOrder(request, "VTTC0801U"); // 모의투자 매도 tr_id
    }
}