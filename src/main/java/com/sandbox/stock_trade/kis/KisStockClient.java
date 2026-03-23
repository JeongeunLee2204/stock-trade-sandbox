package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KisStockClient {

    private final KisConfig kisConfig;
    private final KisTokenClient kisTokenClient;

    public KisBalanceResponse fetchBalance(String accountNo, String productCode) {
        String token = kisTokenClient.fetchAccessToken();
        WebClient client = WebClient.create(kisConfig.getBaseUrl());

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/trading/inquire-balance")
                        .queryParam("CANO", accountNo)
                        .queryParam("ACNT_PRDT_CD", productCode)
                        .queryParam("AFHR_FLPR_YN", "N")
                        .queryParam("OFL_YN", "")
                        .queryParam("INQR_DVSN", "02")
                        .queryParam("UNPR_DVSN", "01")
                        .queryParam("FUND_STTL_ICLD_YN", "N")
                        .queryParam("FNCG_AMT_AUTO_RDPT_YN", "N")
                        .queryParam("PRCS_DVSN", "01")
                        .queryParam("CTX_AREA_FK100", "")
                        .queryParam("CTX_AREA_NK100", "")
                        .build())
                .header("Authorization", "Bearer " + token)
                .header("appkey", kisConfig.getAppKey())
                .header("appsecret", kisConfig.getAppSecret())
                .header("tr_id", "VTTC8434R")
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(KisBalanceResponse.class)
                .block();
    }
    public KisCurrentPriceResponse fetchCurrentPrice(String stockCode) {
        String token = kisTokenClient.fetchAccessToken();
        WebClient client = WebClient.create(kisConfig.getBaseUrl());

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/inquire-price")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                        .queryParam("FID_INPUT_ISCD", stockCode)
                        .build())
                .header("Authorization", "Bearer " + token)
                .header("appkey", kisConfig.getAppKey())
                .header("appsecret", kisConfig.getAppSecret())
                .header("tr_id", "FHKST01010100")
                .header("custtype", "P")
                .header("Content-Type", "application/json")

                .retrieve()
                .bodyToMono(KisCurrentPriceResponse.class)
                .block();
    }
}