package nl.thecheerfuldev.exampletestingwithspring.validation;

import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StartsWithLetterValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void isInvalid() {
        Person person = Person.builder()
                .name("Kram")
                .build();

        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        assertThat(validate)
                .hasSize(1)
                .first()
                .extracting("messageTemplate").isEqualTo("{StartsWithLetterValidator.incorrect}");
    }

    @Test
    void isValid() {
        Person person = Person.builder()
                .name("Mark")
                .build();

        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        assertThat(validate).isEmpty();
    }

    @Test
    void isValidWithIgnoreCase() {
        MyOwnPerson person = new MyOwnPerson("mark");

        Set<ConstraintViolation<MyOwnPerson>> validate = validator.validate(person);
        assertThat(validate).isEmpty();
    }

    @Test
    void isInvalidWithIgnoreCase() {
        MyOwnPerson person = new MyOwnPerson("kram");

        Set<ConstraintViolation<MyOwnPerson>> validate = validator.validate(person);
        assertThat(validate)
                .hasSize(1)
                .first()
                .extracting("messageTemplate").isEqualTo("{StartsWithLetterValidator.incorrect}");
    }

    public static class MyOwnPerson {

        @StartsWith(value = "M", ignoreCase = true)
        private final String name;

        public MyOwnPerson(String name) {
            this.name = name;
        }
    }
}
