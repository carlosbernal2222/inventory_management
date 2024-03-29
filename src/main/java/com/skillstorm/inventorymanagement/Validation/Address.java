package com.skillstorm.inventorymanagement.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "^[a-zA-Z0-9\\s,#.-]*$")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Address {
    String message() default "Invalid address format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
