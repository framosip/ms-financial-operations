package br.com.teknologi.financial.operation.domain.model;

import br.com.teknologi.financial.operation.domain.constant.OperationSubTypeEnum;
import br.com.teknologi.financial.operation.domain.constant.OperationTypeEnum;
import br.com.teknologi.financial.operation.domain.exception.OperationException;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Operation {

    private UUID id;
    private final OperationTypeEnum type;
    private final OperationSubTypeEnum subType;
    private final BigDecimal value;
    private final Set<Installment> installments;
    private final Set<Payment> paidValues;
    private final YearMonth period;
    private String observations;

    public Operation(OperationTypeEnum type, OperationSubTypeEnum subType,
                     YearMonth period, BigDecimal value,
                     Set<Installment> installments, Set<Payment> paidValues) {

        this.type = Objects.requireNonNull(type, "Type is required");
        this.subType = Objects.requireNonNull(subType, "Subtype is required");
        this.period = Objects.requireNonNull(period, "Period is required");
        this.value = Objects.requireNonNull(value, "Value is required");
        this.installments = installments;
        this.paidValues = paidValues;

        if(OperationSubTypeEnum.BASIC.equals(this.subType) &&
                (Objects.isNull(this.paidValues) || this.paidValues.isEmpty())){
            throw new OperationException("OPE0001", "Paid values are required when subType is " + OperationSubTypeEnum.BASIC);
        }

        if(OperationSubTypeEnum.BASIC.equals(this.subType) &&
                (Objects.nonNull(this.installments))){
            throw new OperationException("OPE0002", "When subType is " + OperationSubTypeEnum.BASIC + " installments must be null");
        }

        if((OperationSubTypeEnum.INSTALLMENT.equals(this.subType) || OperationSubTypeEnum.CREDITCARD.equals(this.subType)) &&
                (Objects.isNull(this.installments) || this.installments.isEmpty())){
            throw new OperationException("OPE0003", "Installments are required when subType is " + OperationSubTypeEnum.INSTALLMENT + " or " + OperationSubTypeEnum.CREDITCARD);
        }

        if((OperationSubTypeEnum.INSTALLMENT.equals(this.subType) || OperationSubTypeEnum.CREDITCARD.equals(this.subType)) &&
                (Objects.nonNull(this.paidValues))){
            throw new OperationException("OPE0004", "When subType is " + OperationSubTypeEnum.INSTALLMENT + " or " + OperationSubTypeEnum.CREDITCARD + " paid values must be null");
        }

        if(this.getPaidOff().compareTo(this.value) > 0){
            throw new OperationException("OPE0005", "The total paid off is greater than the operation value");
        }

    }

    public Operation(UUID id, OperationTypeEnum type, OperationSubTypeEnum subType,
                     YearMonth period, BigDecimal value,
                     Set<Installment> installments, Set<Payment> paidValues) {
        this(type, subType, period, value, installments, paidValues);
        this.id = Objects.requireNonNull(id, "Id is required");
    }

    public Operation(UUID id, Operation operation){
        this(id, operation.getType(), operation.getSubType(), operation.period, operation.value,
                operation.getInstallments(), operation.paidValues);
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

    public Set<Installment> getInstallments() {
        return installments;
    }

    public Set<Payment> getPaidValues() {
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

    public BigDecimal getPaidOff(){
        if(OperationSubTypeEnum.BASIC.equals(this.subType)){
            return this.paidValues.stream()
                    .map(Payment::getPaidValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return null;
    }
}
