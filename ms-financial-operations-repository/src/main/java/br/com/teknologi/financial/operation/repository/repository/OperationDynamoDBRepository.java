package br.com.teknologi.financial.operation.repository.repository;

import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.domain.repository.OperationRepository;
import br.com.teknologi.financial.operation.repository.entity.OperationEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class OperationDynamoDBRepository implements OperationRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<OperationEntity> customerDynamoDbAsyncTable;

    public OperationDynamoDBRepository(DynamoDbEnhancedAsyncClient asyncClient){
        this.enhancedAsyncClient = asyncClient;
        this.customerDynamoDbAsyncTable = enhancedAsyncClient.table(OperationEntity.class.getSimpleName(), TableSchema.fromBean(OperationEntity.class));
    }

    @Override
    public Mono<Operation> save(Operation operation) {
        return null;
    }
}
