package br.com.teknologi.financial.operation.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "OperationResponse")
public class OperationResponse {

    @NonNull
    @Schema(description = "Operation id", type = "string", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

}
