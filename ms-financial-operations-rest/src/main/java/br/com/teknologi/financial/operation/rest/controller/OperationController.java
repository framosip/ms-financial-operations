package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.domain.service.OperationService;
import br.com.teknologi.financial.operation.rest.model.request.OperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Tag(name = "Operations", description = "Operations APIs")
public abstract class OperationController<T extends OperationRequest> {

    protected final ModelMapper modelMapper;
    protected final OperationService operationService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<OperationResponse>> save(T operation, UriComponentsBuilder componentsBuilder) {
        return Mono.just(modelMapper.map(operation, br.com.teknologi.financial.operation.domain.model.Operation.class))
                .flatMap(operationService::save)
                .map(operationSaved -> modelMapper.map(operationSaved, OperationResponse.class))
                .flatMap(operationResponse -> Mono.just(componentsBuilder.path(getCreatedURI()).buildAndExpand(operationResponse.getId())).zipWith(Mono.just(operationResponse)))
                .map(responseTuple -> ResponseEntity.created(responseTuple.getT1().toUri()).body(responseTuple.getT2()));        
    }

    protected abstract String getCreatedURI();

}
