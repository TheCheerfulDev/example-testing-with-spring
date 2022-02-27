package nl.thecheerfuldev.exampletestingwithspring.service;

import lombok.AllArgsConstructor;
import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import nl.thecheerfuldev.exampletestingwithspring.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public Person save(final Person person) {
        return this.personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person getPersonById(final long id) {
        return this.personRepository.getById(id);
    }
}
