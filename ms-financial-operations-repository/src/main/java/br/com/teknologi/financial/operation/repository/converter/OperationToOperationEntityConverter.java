package br.com.teknologi.financial.operation.repository.converter;

import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.repository.entity.OperationEntity;
import br.com.teknologi.financial.operation.repository.entity.PaymentEntity;
import lombok.NonNull;
import org.modelmapper.AbstractConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class OperationToOperationEntityConverter extends AbstractConverter<Operation, OperationEntity> {

    @Override
    protected OperationEntity convert(@NonNull Operation operation) {
        OperationEntity operationEntity = new OperationEntity(
                operation.getId().toString(),
                "T#"
                        .concat(operation.getType().getCode())
                        .concat("/S#")
                        .concat(operation.getSubType().getCode())
                        .concat("/P#")
                        .concat(operation.getPeriod().format(DateTimeFormatter.ofPattern("yyyyMM"))),
                operation.getType().name(),
                operation.getSubType().name(),
                operation.getValue(),
                operation.getPeriod()
        );

        operationEntity.setPaidValues(
                operation.getPaidValues().stream()
                        .map(p -> new PaymentEntity(
                                operation.getId().toString(),
                                p.getDate(),
                                p.getPaidValue()
                        ))
                        .collect(Collectors.toList())
        );

        return operationEntity;
    }

}
