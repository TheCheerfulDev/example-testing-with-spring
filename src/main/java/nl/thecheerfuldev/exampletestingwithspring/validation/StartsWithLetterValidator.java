package nl.thecheerfuldev.exampletestingwithspring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithLetterValidator implements ConstraintValidator<StartsWith, String> {

    private String expected;
    private boolean ignoreCase;

    @Override
    public void initialize(StartsWith constraintAnnotation) {
        this.expected = constraintAnnotation.value();
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        if (ignoreCase) {
            return s.substring(0, expected.length()).equalsIgnoreCase(expected);
        }
        return s.startsWith(expected);
    }
}
