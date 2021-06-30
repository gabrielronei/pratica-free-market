package br.com.gabriels.praticafreemarket.infra.annotation.uniqueField;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueFieldValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniqueField {

    String nomeCampo();

    Class<?> classeDominio();

    String message() default "Já existe um campo com este valor!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

