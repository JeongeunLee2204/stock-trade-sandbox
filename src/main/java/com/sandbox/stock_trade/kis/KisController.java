package com.sandbox.stock_trade.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kis")
@RequiredArgsConstructor
public class KisController {

    private final KisTokenClient kisTokenClient;

    @GetMapping("/token")
    public String getToken() {
        return kisTokenClient.fetchAccessToken();
    }
}