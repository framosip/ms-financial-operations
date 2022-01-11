package br.com.teknologi.financial.operation.domain.repository;

import br.com.teknologi.financial.operation.domain.model.Operation;
import reactor.core.publisher.Mono;

public interface OperationRepository {

    Mono<Operation> save(Operation operation);
}
