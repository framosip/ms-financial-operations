package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.rest.model.request.OperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/operations")
@Tag(name = "Operations", description = "Operations APIs")
public class OperationController {

    @Operation(summary = "Create an operation", description = "Create an operation that can be an expense or an income", tags = { "Operations" })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<OperationResponse>> save(
            @NonNull @Valid @RequestBody OperationRequest operation) {
        return Mono.empty();
    }

}
