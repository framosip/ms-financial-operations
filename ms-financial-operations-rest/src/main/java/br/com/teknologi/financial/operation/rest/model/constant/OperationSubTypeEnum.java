package br.com.teknologi.financial.operation.rest.model.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(implementation = OperationSubTypeEnum.class, type = "string")
public enum OperationSubTypeEnum {

    SINGLE("S"),
    INSTALLMENT("I"),
    CREDITCARD("C");

    private final String id;

    OperationSubTypeEnum(String id) {
        this.id = id;
    }

    @JsonValue
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
