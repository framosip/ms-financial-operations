package br.com.teknologi.financial.operation.domain.model;

import br.com.teknologi.financial.operation.domain.exception.OperationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Payment {

    private final LocalDate date;
    private final BigDecimal paidValue;

    public Payment(LocalDate date, BigDecimal paidValue) {
        this.date = Objects.requireNonNull(date, "Date is required");
        this.paidValue = Objects.requireNonNull(paidValue, "PaidValue is required");

        if(this.getDate().isBefore(LocalDate.now())){
            throw new OperationException("PAY0001", "The date should be equal today or future");
        }

        if(this.getPaidValue().compareTo(BigDecimal.ZERO) <= 0){
            throw new OperationException("PAY0002", "The paid value should be positive and greater than zero");
        }
    }

    public BigDecimal getPaidValue() {
        return paidValue;
    }

    public LocalDate getDate() {
        return date;
    }
}
