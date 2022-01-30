package br.com.teknologi.financial.operation.repository.entity;

import br.com.teknologi.financial.operation.repository.converter.YearMonthAttributeConverter;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@DynamoDbBean
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
public class OperationEntity {

    private UUID id;

    @NonNull
    private String type;

    @NonNull
    private String subType;

    @NonNull
    private BigDecimal value;

    private List<InstallmentEntity> installments;

    private List<PaymentEntity> paidValues;

    @NonNull
    private YearMonth period;

    private String observations;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }

    @DynamoDbConvertedBy(YearMonthAttributeConverter.class)
    public YearMonth getPeriod() {
        return period;
    }
}
