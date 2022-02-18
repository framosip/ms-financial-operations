package br.com.teknologi.financial.operation.rest.converter;

import br.com.teknologi.financial.operation.domain.constant.OperationSubTypeEnum;
import br.com.teknologi.financial.operation.domain.constant.OperationTypeEnum;
import br.com.teknologi.financial.operation.domain.model.Operation;
import br.com.teknologi.financial.operation.domain.model.Payment;
import br.com.teknologi.financial.operation.rest.model.request.BasicOperationRequest;
import lombok.NonNull;
import org.modelmapper.AbstractConverter;

import java.time.LocalDate;
import java.util.Set;

public class BasicOperationRequestToOperationConverter extends AbstractConverter<BasicOperationRequest, Operation> {

    @Override
    protected Operation convert(@NonNull BasicOperationRequest basicOperationRequest) {

        Set<Payment> paidValues = Set.of(new Payment(LocalDate.now(), basicOperationRequest.getPaidValue()));

        return new Operation(OperationTypeEnum.valueOf(basicOperationRequest.getType().toString()), OperationSubTypeEnum.BASIC,
                basicOperationRequest.getPeriod(), basicOperationRequest.getValue(), null,
                paidValues);
    }
}
