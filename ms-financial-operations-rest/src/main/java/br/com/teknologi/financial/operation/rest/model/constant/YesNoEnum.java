package br.com.teknologi.financial.operation.rest.model.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(implementation = YesNoEnum.class, type = "string")
public enum YesNoEnum {

    YES("Y"),
    NO("N");

    private String id;

    YesNoEnum(String id) {
        this.id = id;
    }

    @JsonValue
    public String getId() {
        return id;
    }
}
