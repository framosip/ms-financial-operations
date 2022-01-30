package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.domain.service.OperationService;
import br.com.teknologi.financial.operation.rest.model.request.BasicOperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/operations/basics")
public class BasicOperationController extends OperationController<BasicOperationRequest> {

    public BasicOperationController(ModelMapper modelMapper, OperationService operationService) {
        super(modelMapper, operationService);
    }

    @Override
    @Operation(summary = "Create a basic operation", description = "Create a basic operation that can be an expense or an income", tags = { "Operations" })
    public Mono<ResponseEntity<OperationResponse>> save(
            @NonNull @Valid @RequestBody BasicOperationRequest operation,
            UriComponentsBuilder componentsBuilder) {
        return super.save(operation, componentsBuilder);
    }

    @Override
    protected String getCreatedURI() {
        return "/operations/basics/{id}";
    }
}
