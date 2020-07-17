package io.spring.workshop.stockquotes;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

@Getter
@Setter
public class Quote {
    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;

    private BigDecimal price;

    private Instant instant = Instant.now();

    public Quote() {
    }

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Quote(String ticker, Double price) {
        this(ticker, new BigDecimal(price, MATH_CONTEXT));
    }
}
