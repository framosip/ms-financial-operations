package br.com.teknologi.financial.operation.repository.repository;

import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.domain.repository.OperationRepository;
import br.com.teknologi.financial.operation.repository.entity.OperationEntity;
import br.com.teknologi.financial.operation.repository.entity.PaymentEntity;
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
    private final DynamoDbAsyncTable<PaymentEntity> paymentEntityDynamoDbAsyncTable;

    private final ModelMapper modelMapper;

    @Override
    public Mono<Operation> save(@NonNull Operation operation) {
        OperationEntity operationEntity = modelMapper.map(operation, OperationEntity.class);
        operationDynamoDbAsyncTable.putItem(operationEntity).join();

        if(operationEntity.getPaidValues() != null && !operationEntity.getPaidValues().isEmpty()){
            operationEntity.getPaidValues().forEach(p -> paymentEntityDynamoDbAsyncTable.putItem(p).join());
        }

        return Mono.just(operation);
    }
}
