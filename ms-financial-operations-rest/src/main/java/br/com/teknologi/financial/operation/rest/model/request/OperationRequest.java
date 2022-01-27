package br.com.teknologi.financial.operation.rest.model.request;

import br.com.teknologi.financial.operation.rest.model.constant.OperationTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class OperationRequest {

    @NonNull
    @NotBlank
    @Schema(description = "Operation description", type = "string", example = "Salary", required = true)
    private String description;

    @NonNull
    @NotNull
    @Schema(description = "Operation type", required = true)
    private OperationTypeEnum type;

    @NonNull
    @NotNull
    @Positive
    @Digits(integer=18, fraction=2)
    @Schema(description = "Operation value", type = "number", format = "double", example = "32.56", required = true)
    private BigDecimal value;

    @Size(min = 2)
    @Schema(description = "Observations", type = "string", example = "Amount spent in the month of Christmas")
    private String observations;

}

