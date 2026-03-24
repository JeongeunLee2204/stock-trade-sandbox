package com.sandbox.stock_trade.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KisOrderRequest {

    @JsonProperty("CANO")
    private String accountNo;

    @JsonProperty("ACNT_PRDT_CD")
    private String productCode;

    @JsonProperty("PDNO")
    private String stockCode;

    @JsonProperty("ORD_DVSN")
    private String orderType;

    @JsonProperty("ORD_QTY")
    private String orderQty;

    @JsonProperty("ORD_UNPR")
    private String orderPrice;
}