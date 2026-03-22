package com.sandbox.stock_trade;

import com.sandbox.stock_trade.kis.KisTokenClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KisTokenClientTest {

    @Autowired
    private KisTokenClient kisTokenClient;

    @Test
    void fetchAccessToken_success() {
        String token = kisTokenClient.fetchAccessToken();

        System.out.println("Access Token: " + token);
        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();
    }
}