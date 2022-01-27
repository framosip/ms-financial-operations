package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.domain.service.OperationService;
import br.com.teknologi.financial.operation.rest.model.request.InstallmentOperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations/installments")
public class InstallmentOperationController extends OperationController<InstallmentOperationRequest> {


    public InstallmentOperationController(ModelMapper modelMapper, OperationService operationService) {
        super(modelMapper, operationService);
    }

    @Override
    @Operation(summary = "Create an installment operation", description = "Create an installment operation that can be an expense or an income", tags = { "Operations" })
    public Mono<ResponseEntity<OperationResponse>> save(InstallmentOperationRequest operation, UriComponentsBuilder componentsBuilder) {
        return super.save(operation, componentsBuilder);
    }

    @Override
    protected String getCreatedURI() {
        return "/operations/installments/{id}";
    }
}
