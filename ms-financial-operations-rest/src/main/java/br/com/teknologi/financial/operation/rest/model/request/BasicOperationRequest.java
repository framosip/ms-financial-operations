package br.com.teknologi.financial.operation.rest.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicOperationRequest extends OperationRequest{

    @NonNull
    @NotNull
    @PositiveOrZero
    @Digits(integer=18, fraction=2)
    @Schema(description = "Operation paid value", type = "decimal", example = "12.56", required = true)
    private BigDecimal paidValue;

    @Schema(description = "Operation period", type = "string", example = "2021-01")
    private YearMonth period;
}
