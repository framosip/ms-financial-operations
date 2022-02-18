package br.com.teknologi.financial.operation.domain.constant;

public enum OperationTypeEnum {

    INCOME("I"),
    EXPENSE("E");

    private final String code;

    OperationTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
