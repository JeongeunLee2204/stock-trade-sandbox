package com.sandbox.stock_trade.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KisOrderResponse {

    @JsonProperty("rt_cd")
    private String resultCode;

    @JsonProperty("msg_cd")
    private String messageCode;

    @JsonProperty("msg1")
    private String message;
}