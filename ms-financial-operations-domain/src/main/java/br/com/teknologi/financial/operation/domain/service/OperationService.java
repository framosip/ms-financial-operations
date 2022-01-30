package br.com.teknologi.financial.operation.domain.service;

import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.domain.repository.OperationRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Mono<Operation> insert(Operation operation){
        return operationRepository.save(new Operation(UUID.randomUUID(), operation));
    }

}