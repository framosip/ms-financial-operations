package br.com.teknologi.financial.operation.rest.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentOperationRequest extends OperationRequest{

    @NonNull
    @NotNull
    @Min(value = 2)
    @Schema(description = "Number of installments", type = "integer", example = "5")
    private Integer installments;

    @NonNull
    @NotNull
    @Schema(description = "Initial installment period", type = "string", example = "2021-01")
    private YearMonth initialInstallment;
}
