package umc.spring.validation.annotation;

import org.springframework.web.bind.annotation.ControllerAdvice;
import umc.spring.validation.validator.RegionsExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@ControllerAdvice
@Constraint(validatedBy = RegionsExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegions {

    String message() default "해당하는 지역이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
