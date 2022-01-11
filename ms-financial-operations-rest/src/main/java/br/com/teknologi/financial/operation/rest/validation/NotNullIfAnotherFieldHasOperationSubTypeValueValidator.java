package br.com.teknologi.financial.operation.rest.validation;

import br.com.teknologi.financial.operation.rest.model.constant.OperationSubTypeEnum;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullIfAnotherFieldHasOperationSubTypeValueValidator implements ConstraintValidator<NotNullIfAnotherFieldHasOperationSubTypeValue, Object> {

    private String fieldName;
    private OperationSubTypeEnum expectedFieldValue;
    private String dependField;

    @Override
    public void initialize(NotNullIfAnotherFieldHasOperationSubTypeValue annotation) {
        fieldName          = annotation.field();
        expectedFieldValue = annotation.value();
        dependField = annotation.dependField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        if (value == null) {
            return true;
        }

        try {
            BeanWrapper ovalue = new BeanWrapperImpl(value);
            OperationSubTypeEnum fieldValue = (OperationSubTypeEnum) ovalue.getPropertyValue(fieldName);
            Object dependFieldValue         = ovalue.getPropertyValue(dependField);

            if (expectedFieldValue.equals(fieldValue) && dependFieldValue == null) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                        .addPropertyNode(dependField)
                        .addConstraintViolation();
                return false;
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }
}
