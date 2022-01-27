package br.com.teknologi.financial.operation.rest.controller;

import br.com.teknologi.financial.operation.domain.service.OperationService;
import br.com.teknologi.financial.operation.rest.model.request.CreditCardOperationRequest;
import br.com.teknologi.financial.operation.rest.model.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations/creditcards")
public class CreditCardOperationController extends OperationController<CreditCardOperationRequest> {

    public CreditCardOperationController(ModelMapper modelMapper, OperationService operationService) {
        super(modelMapper, operationService);
    }

    @Override
    @Operation(summary = "Create a credit card operation", description = "Create a credit card operation that can be an expense or an income", tags = { "Operations" })
    public Mono<ResponseEntity<OperationResponse>> save(CreditCardOperationRequest operation, UriComponentsBuilder componentsBuilder) {
        return super.save(operation, componentsBuilder);
    }

    @Override
    protected String getCreatedURI() {
        return "/operations/creditcards/{id}";
    }
}
