package br.com.teknologi.financial.operation.domain.constant;

public enum OperationSubTypeEnum {

    BASIC("B"),
    INSTALLMENT("I"),
    CREDITCARD("C");

    private final String code;

    OperationSubTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
