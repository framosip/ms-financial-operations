package br.com.teknologi.financial.operation.rest.model.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(implementation = OperationTypeEnum.class, type = "string")
public enum OperationTypeEnum {

    INCOME("I"),
    EXPENSE("E");

    private String id;

    OperationTypeEnum(String id) {
        this.id = id;
    }

    @JsonValue
    public String getId() {
        return id;
    }
}
