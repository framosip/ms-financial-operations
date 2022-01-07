package br.com.teknologi.financial.operation.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(AnyShouldBeYes.AnyShouldBeYesContainer.class)
@Constraint(validatedBy = AnyShouldBeYesValidator.class)
@Documented
public @interface AnyShouldBeYes {

    String[] fields();

    String message() default "{validator.anyshouldbeyes}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface AnyShouldBeYesContainer {
        AnyShouldBeYes[] value();
    }
}
