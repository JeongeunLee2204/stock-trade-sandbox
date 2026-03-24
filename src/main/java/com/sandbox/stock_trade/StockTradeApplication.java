package com.sandbox.stock_trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTradeApplication.class, args);
	}

}
