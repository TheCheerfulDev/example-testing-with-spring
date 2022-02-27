package nl.thecheerfuldev.exampletestingwithspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nl.thecheerfuldev.exampletestingwithspring.validation.StartsWith;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @StartsWith(value = "M", ignoreCase = true)
    private String name;
    @Min(0)
    @Max(100)
    private int age;
    @Email
    private String email;

    public Person() {
    }

}
