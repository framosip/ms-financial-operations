package br.com.teknologi.financial.operation.repository.configuration;

import br.com.teknologi.financial.operation.repository.entity.OperationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DynamoAsyncTablesConfiguration {

    private static final String COLLECTION_OPERATION = "teknologi_financial_operations";
    private final DynamoDbEnhancedAsyncClient asyncClient;

    @Bean
    public DynamoDbAsyncTable<OperationEntity> getOperationTransactionTable(){
        log.debug("[Configuration] ===== Operation Transaction DynamoDbAsyncTable bean configured =====");
        return asyncClient.table(COLLECTION_OPERATION, TableSchema.fromBean(OperationEntity.class));
    }
}
