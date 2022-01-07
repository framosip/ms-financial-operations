package br.com.teknologi.financial.operation.rest.model.request;

import br.com.teknologi.financial.operation.rest.model.constant.OperationTypeEnum;
import br.com.teknologi.financial.operation.rest.model.constant.YesNoEnum;
import br.com.teknologi.financial.operation.rest.validation.AnyShouldBeYes;
import br.com.teknologi.financial.operation.rest.validation.NotNullIfFieldIsYes;
import br.com.teknologi.financial.operation.rest.validation.ShouldBeNoIfFieldIsYes;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "OperationRequest")
@AnyShouldBeYes(fields = {"isInstallments","isCreditCard"})
@ShouldBeNoIfFieldIsYes(field = "isInstallments", dependField = "isCreditCard")
@ShouldBeNoIfFieldIsYes(field = "isCreditCard", dependField = "isInstallments")
@NotNullIfFieldIsYes(field = "isInstallments", dependField = "installments")
@NotNullIfFieldIsYes(field = "isInstallments", dependField = "initialInstallment")
@NotNullIfFieldIsYes(field = "isCreditCard", dependField = "creditCardOperationDate")
public class OperationRequest {

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
    @Schema(description = "Is it installments?", required = true)
    private YesNoEnum isInstallments;

    @NonNull
    @NotNull
    @Schema(description = "Is it credit card operation?", required = true)
    private YesNoEnum isCreditCard;

    @NonNull
    @NotNull
    @Positive
    @Digits(integer=18, fraction=2)
    @Schema(description = "Operation value", type = "number", format = "double", example = "32.56", required = true)
    private BigDecimal value;

    @Schema(description = "Number of installments", type = "integer", example = "5")
    private Integer installments;

    @Schema(description = "Initial installment period", type = "string", example = "2021-01")
    private YearMonth initialInstallment;

    @Schema(description = "Credit card operation date", type = "string", format = "date", example = "2021-01-10")
    private LocalDate creditCardOperationDate;

    @NonNull
    @NotNull
    @PositiveOrZero
    @Digits(integer=18, fraction=2)
    @Schema(description = "Operation paid value", type = "decimal", example = "12.56", required = true)
    private BigDecimal paidValue;

    @Schema(description = "Operation period", type = "string", example = "2021-01")
    private YearMonth period = YearMonth.now();

    @Schema(description = "Observations", type = "string", example = "Amount spent in the month of Christmas")
    private String observations;

}
