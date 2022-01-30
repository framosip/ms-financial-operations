package br.com.teknologi.financial.operation.domain.model;

import java.math.BigDecimal;

public class Payment {

    private final BigDecimal paidValue;

    public Payment(BigDecimal paidValue) {
        this.paidValue = paidValue;
    }

    public BigDecimal getPaidValue() {
        return paidValue;
    }

}
