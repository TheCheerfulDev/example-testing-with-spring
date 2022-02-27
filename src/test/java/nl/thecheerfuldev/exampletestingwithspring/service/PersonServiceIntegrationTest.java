package nl.thecheerfuldev.exampletestingwithspring.service;

import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import nl.thecheerfuldev.exampletestingwithspring.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@DataJpaTest
//includeFilters = {
//@ComponentScan.Filter(type = ASSIGNABLE_TYPE, classes = PersonService.class)
//}
@Import(PersonService.class)
@AutoConfigureTestDatabase(replace = ANY)
class PersonServiceIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService sut;

    @Test
    void save() {
        Person input = Person.builder()
                .name("Mark")
                .age(36)
                .email("a@b.nl")
                .build();
        sut.save(input);

        Person actual = personRepository.getById(1L);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(input);



    }

}
