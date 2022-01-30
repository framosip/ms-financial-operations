package br.com.teknologi.financial.operation.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @NonNull
    @Schema(description = "Error code", type = "string", example = "000.000", required = true)
    private String code;

    @Schema(description = "Field name", type = "string", example = "description")
    private String field;

    @NonNull
    @Schema(description = "Error description", type = "string", example = "Field is required", required = true)
    private String description;

}
