package br.com.teknologi.financial.operation.rest.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardOperationRequest extends OperationRequest {

    @NonNull
    @NotBlank
    @Schema(description = "Credit card identification", type = "string", example = "123e4567-e89b-12d3-a456-426614174000")
    private String creditCard;

    @NonNull
    @PastOrPresent
    @Schema(description = "Credit card operation date", type = "string", format = "date", example = "2021-01-10")
    private LocalDate creditCardOperationDate;

    @NonNull
    @NotNull
    @Min(value = 1)
    @Schema(description = "Number of credit card installments", type = "integer", example = "5")
    private Integer creditCardInstallments;


}
