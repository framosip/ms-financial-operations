package br.com.teknologi.financial.operation.rest.validation;

import br.com.teknologi.financial.operation.rest.model.constant.YesNoEnum;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullIfFieldIsYesValidator implements ConstraintValidator<NotNullIfFieldIsYes, Object> {

    private String field;
    private String dependField;

    @Override
    public void initialize(NotNullIfFieldIsYes annotation) {
        field = annotation.field();
        dependField = annotation.dependField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }

        try {
            BeanWrapper ovalue = new BeanWrapperImpl(value);

            YesNoEnum fieldValue = (YesNoEnum) ovalue.getPropertyValue(field);
            Object dependFieldValue = ovalue.getPropertyValue(dependField);

            if (YesNoEnum.YES.equals(fieldValue) && dependFieldValue == null) {
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
