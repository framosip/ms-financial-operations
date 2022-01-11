package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.domain.service.OperationService;
import br.com.teknologi.financial.operation.rest.model.request.OperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/operations")
@Tag(name = "Operations", description = "Operations APIs")
@RequiredArgsConstructor
public class OperationController {

    private final ModelMapper modelMapper;
    private final OperationService operationService;

    @Operation(summary = "Create an operation", description = "Create an operation that can be an expense or an income", tags = { "Operations" })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<OperationResponse>> save(
            @NonNull @Valid @RequestBody OperationRequest operation,
            UriComponentsBuilder componentsBuilder) {
        return Mono.just(modelMapper.map(operation, br.com.teknologi.financial.operation.domain.model.Operation.class))
                .flatMap(operationService::save)
                .map(operationSaved -> modelMapper.map(operationSaved, OperationResponse.class))
                .flatMap(operationResponse -> Mono.just(componentsBuilder.path("/operations/{id}").buildAndExpand(operationResponse.getId())).zipWith(Mono.just(operationResponse)))
                .map(responseTuple -> ResponseEntity.created(responseTuple.getT1().toUri()).body(responseTuple.getT2()));
    }

}
