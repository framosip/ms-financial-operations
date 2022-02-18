package br.com.teknologi.financial.operation.repository.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.time.LocalDate;

@DynamoDbBean
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
public class PaymentEntity {

    @NonNull
    private String pk;

    @NonNull
    private LocalDate date;

    @NonNull
    private BigDecimal paidValue;

    @DynamoDbPartitionKey
    public String getPk() {
        return pk;
    }

}
