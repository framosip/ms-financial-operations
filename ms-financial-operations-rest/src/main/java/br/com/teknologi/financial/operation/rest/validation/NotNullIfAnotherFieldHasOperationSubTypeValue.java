package br.com.teknologi.financial.operation.rest.validation;

import br.com.teknologi.financial.operation.rest.model.constant.OperationSubTypeEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(NotNullIfAnotherFieldHasOperationSubTypeValue.NotNullIfAnotherFieldHasOperationSubTypeValueContainer.class)
@Constraint(validatedBy = NotNullIfAnotherFieldHasOperationSubTypeValueValidator.class)
@Documented
public @interface NotNullIfAnotherFieldHasOperationSubTypeValue {

    String field();
    OperationSubTypeEnum value();
    String dependField();

    String message() default "{validator.notnullifanotherfieldhasvalue}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface NotNullIfAnotherFieldHasOperationSubTypeValueContainer {
        NotNullIfAnotherFieldHasOperationSubTypeValue[] value();
    }
}
