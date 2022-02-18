package br.com.teknologi.financial.operation.repository.entity;

import br.com.teknologi.financial.operation.repository.converter.YearMonthAttributeConverter;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@DynamoDbBean
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@RequiredArgsConstructor
public class OperationEntity {

    @NonNull
    private String pk;

    @NonNull
    private String sk;

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
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    public String getSk() {
        return sk;
    }

    @DynamoDbIgnore
    public List<PaymentEntity> getPaidValues() {
        return paidValues;
    }

    @DynamoDbConvertedBy(YearMonthAttributeConverter.class)
    public YearMonth getPeriod() {
        return period;
    }
}
