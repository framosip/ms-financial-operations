package br.com.teknologi.financial.operation.repository.repository;

import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.domain.repository.OperationRepository;
import br.com.teknologi.financial.operation.repository.entity.OperationEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;

@Repository
@RequiredArgsConstructor
public class OperationDynamoDBRepository implements OperationRepository {

    private final DynamoDbAsyncTable<OperationEntity> operationDynamoDbAsyncTable;
    private final ModelMapper modelMapper;

    @Override
    public Mono<Operation> save(@NonNull Operation operation) {
        OperationEntity operationEntity = modelMapper.map(operation, OperationEntity.class);
        operationDynamoDbAsyncTable.putItem(operationEntity).join();
        return Mono.just(operation);
    }
}
