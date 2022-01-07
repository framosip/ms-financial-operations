package br.com.teknologi.financial.operation.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(ShouldBeNoIfFieldIsYes.ShouldBeNoIfFieldIsYesContainer.class)
@Constraint(validatedBy = ShouldBeNoIfFieldIsYesValidator.class)
@Documented
public @interface ShouldBeNoIfFieldIsYes {

    String field();
    String dependField();

    String message() default "{validator.shouldbenoiffieldisyes}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface ShouldBeNoIfFieldIsYesContainer {
        ShouldBeNoIfFieldIsYes[] value();
    }
}
