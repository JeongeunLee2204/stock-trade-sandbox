package com.sandbox.stock_trade.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
public class KisBalanceResponse {

    @JsonProperty("output2")
    private List<Balance> output2;

    @Getter
    public static class Balance {
        @JsonProperty("dnca_tot_amt")
        private String totalAmount;

        @JsonProperty("prvs_rcdl_excc_amt")
        private String withdrawableAmount;
    }
}