package com.sandbox.stock_trade.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KisCurrentPriceResponse {

    @JsonProperty("output")
    private Price output;

    @Getter
    public static class Price {
        @JsonProperty("stck_prpr")
        private String currentPrice;

        @JsonProperty("hts_kor_isnm")
        private String stockName;
    }
}