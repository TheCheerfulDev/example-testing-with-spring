package nl.thecheerfuldev.exampletestingwithspring.repository;

import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@DataJpaTest
@AutoConfigureTestDatabase(replace = ANY)
class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository sut;

    @Test
    void save() {
        sut.save(Person.builder()
                .name("mark")
                .age(36)
                .email("a@b.nl")
                .build());

        Person actual = sut.getById(1L);

        assertThat(actual.getName()).isEqualToIgnoringCase("Mark");

    }

}
