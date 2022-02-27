package nl.thecheerfuldev.exampletestingwithspring.repository;

import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
