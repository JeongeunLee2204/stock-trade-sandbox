package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoTradingScheduler {

    private final KisStockClient kisStockClient;
    private final KisOrderClient kisOrderClient;

    public static final String ACCOUNT_NO = "50178507";
    public static final String PRODUCT_CODE = "01";
    public static final String STOCK_CODE = "005930"; // 삼성전자

    private static final long BUY_THRESHOLD = 70000L;   // 이하면 매수
    private static final long SELL_THRESHOLD = 90000L;  // 이상이면 매도

    @Scheduled(fixedRate = 3600000) // 1시간마다
    public void run() {
        log.info("AutoTrading scheduler started");
        checkAndTrade();
    }

    public String checkAndTrade() {
        KisCurrentPriceResponse priceResponse = kisStockClient.fetchCurrentPrice(STOCK_CODE);
        long currentPrice = Long.parseLong(priceResponse.getOutput().getCurrentPrice());
        log.info("Current price of {}: {}", STOCK_CODE, currentPrice);

        if (currentPrice <= BUY_THRESHOLD) {
            KisOrderResponse response = kisOrderClient.buy(ACCOUNT_NO, PRODUCT_CODE, STOCK_CODE, "1", String.valueOf(currentPrice));
            return "매수 실행 - 현재가: " + currentPrice + "원 / " + response.getMessage();

        } else if (currentPrice >= SELL_THRESHOLD) {
            KisOrderResponse response = kisOrderClient.sell(ACCOUNT_NO, PRODUCT_CODE, STOCK_CODE, "1", String.valueOf(currentPrice));
            return "매도 실행 - 현재가: " + currentPrice + "원 / " + response.getMessage();

        } else {
            return "조건 미충족 - 현재가: " + currentPrice + "원 (매수 기준: " + BUY_THRESHOLD + "원 이하 / 매도 기준: " + SELL_THRESHOLD + "원 이상)";
        }
    }
}