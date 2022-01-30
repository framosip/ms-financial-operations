package br.com.teknologi.financial.operation.repository.converter;

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.YearMonth;

public class YearMonthAttributeConverter implements AttributeConverter<YearMonth> {

    @Override
    public AttributeValue transformFrom(YearMonth yearMonth) {
        return AttributeValue.builder()
                .s(yearMonth.toString())
                .build();
    }

    @Override
    public YearMonth transformTo(AttributeValue attributeValue) {
        return YearMonth.parse(attributeValue.s());
    }

    @Override
    public EnhancedType<YearMonth> type() {
        return EnhancedType.of(YearMonth.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}
