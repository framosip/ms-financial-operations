package br.com.teknologi.financial.operation.rest.validation;

import br.com.teknologi.financial.operation.rest.model.constant.YesNoEnum;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AnyShouldBeYesValidator implements ConstraintValidator<AnyShouldBeYes, Object> {

    private String[] fields;

    @Override
    public void initialize(AnyShouldBeYes annotation) {
        fields = annotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }

        List<String> fields = Arrays.asList(this.fields);
        BeanWrapper ovalue = new BeanWrapperImpl(value);

        return fields.stream()
                .map(field -> (YesNoEnum) ovalue.getPropertyValue(field))
                .anyMatch(YesNoEnum.YES::equals);

    }

}
