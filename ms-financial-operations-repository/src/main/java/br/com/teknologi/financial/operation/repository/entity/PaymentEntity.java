package br.com.teknologi.financial.operation.repository.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.math.BigDecimal;

@DynamoDbBean
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
public class PaymentEntity {

    @NonNull
    private BigDecimal paidValue;
}
