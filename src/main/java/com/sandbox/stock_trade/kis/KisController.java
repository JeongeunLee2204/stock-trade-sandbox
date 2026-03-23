package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kis")
@RequiredArgsConstructor
public class KisController {

    private final KisTokenClient kisTokenClient;
    private final KisStockClient kisStockClient;

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
}