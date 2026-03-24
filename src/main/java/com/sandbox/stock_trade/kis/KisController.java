package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kis")
@RequiredArgsConstructor
public class KisController {

    private final KisTokenClient kisTokenClient;
    private final KisStockClient kisStockClient;
    private final KisOrderClient kisOrderClient;

    @GetMapping("/token")
    public String getToken() {
        return kisTokenClient.fetchAccessToken();
    }
    @GetMapping("/balance")
    public KisBalanceResponse getBalance(
            @RequestParam String accountNo,
            @RequestParam String productCode) {
        return kisStockClient.fetchBalance(accountNo, productCode);
    }

    @GetMapping("/price")
    public KisCurrentPriceResponse getCurrentPrice(@RequestParam String stockCode) {
        return kisStockClient.fetchCurrentPrice(stockCode);
    }
    private final AutoTradingScheduler autoTradingScheduler;

    @PostMapping("/trade/trigger")
    public String triggerTrading() {
        return autoTradingScheduler.checkAndTrade();
    }

    @PostMapping("/trade/buy")
    public KisOrderResponse buyOne() {
        return kisOrderClient.buy(
                AutoTradingScheduler.ACCOUNT_NO,
                AutoTradingScheduler.PRODUCT_CODE,
                AutoTradingScheduler.STOCK_CODE,
                "1", "0"  // 수량 1주, 가격 0 = 시장가
        );
    }

    @PostMapping("/trade/sell")
    public KisOrderResponse sellOne() {
        return kisOrderClient.sell(
                AutoTradingScheduler.ACCOUNT_NO,
                AutoTradingScheduler.PRODUCT_CODE,
                AutoTradingScheduler.STOCK_CODE,
                "1", "0"  // 수량 1주, 가격 0 = 시장가
        );
    }
}