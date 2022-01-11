package br.com.teknologi.financial.operation.domain.model;

import br.com.teknologi.financial.operation.domain.constant.OperationSubTypeEnum;
import br.com.teknologi.financial.operation.domain.constant.OperationTypeEnum;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Operation {

    private UUID id;
    private final OperationTypeEnum type;
    private final OperationSubTypeEnum subType;
    private final BigDecimal value;
    private final List<Installment> installments;
    private final List<Payment> paidValues;
    private final YearMonth period;
    private String observations;

    public Operation(OperationTypeEnum type, OperationSubTypeEnum subType,
                     YearMonth period, BigDecimal value,
                     List<Installment> installments, List<Payment> paidValues) {

        this.type = Objects.requireNonNull(type, "Type is required");
        this.subType = Objects.requireNonNull(subType, "Subtype is required");
        this.period = Objects.requireNonNull(period, "Period is required");
        this.value = Objects.requireNonNull(value, "Value is required");
        this.installments = installments;
        this.paidValues = paidValues;

        if(OperationSubTypeEnum.SINGLE.equals(this.subType) &&
                (Objects.isNull(this.paidValues) || this.paidValues.isEmpty())){
            throw new IllegalArgumentException("Paid values are required when subType is " + OperationSubTypeEnum.SINGLE);
        }

        if(OperationSubTypeEnum.SINGLE.equals(this.subType) &&
                (Objects.nonNull(this.installments))){
            throw new IllegalArgumentException("When subType is " + OperationSubTypeEnum.SINGLE + " installments must be null");
        }

        if((OperationSubTypeEnum.INSTALLMENT.equals(this.subType) || OperationSubTypeEnum.CREDITCARD.equals(this.subType)) &&
                (Objects.isNull(this.installments) || this.installments.isEmpty())){
            throw new IllegalArgumentException("Installments are required when subType is " + OperationSubTypeEnum.INSTALLMENT + " or " + OperationSubTypeEnum.CREDITCARD);
        }

        if((OperationSubTypeEnum.INSTALLMENT.equals(this.subType) || OperationSubTypeEnum.CREDITCARD.equals(this.subType)) &&
                (Objects.nonNull(this.paidValues))){
            throw new IllegalArgumentException("When subType is " + OperationSubTypeEnum.INSTALLMENT + " or " + OperationSubTypeEnum.CREDITCARD + " paid values must be null");
        }

        if(this.getAmount().compareTo(this.value) > 0){
            throw new IllegalArgumentException("The value is less than the total operation");
        }

    }

    public Operation(UUID id, OperationTypeEnum type, OperationSubTypeEnum subType,
                     YearMonth period, BigDecimal value,
                     List<Installment> installments, List<Payment> paidValues) {
        this(type, subType, period, value, installments, paidValues);
        this.id = Objects.requireNonNull(id, "Id is required");
    }

    public UUID getId() {
        return id;
    }

    public OperationTypeEnum getType() {
        return type;
    }

    public OperationSubTypeEnum getSubType() {
        return subType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public List<Payment> getPaidValues() {
        return paidValues;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public BigDecimal getAmount(){
        return null;
    }
}
