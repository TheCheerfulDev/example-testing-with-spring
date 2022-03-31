package nl.thecheerfuldev.exampletestingwithspring.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import nl.thecheerfuldev.exampletestingwithspring.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerRestAssuredIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:14.1")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("testdb");

    @LocalServerPort
    private int port;

    @DynamicPropertySource
    private static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DATABASE::getJdbcUrl);
        registry.add("spring.datasource.username", DATABASE::getUsername);
        registry.add("spring.datasource.password", DATABASE::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void save() {
        given().body("""
                        {
                          "name":"Mark",
                          "age":36,
                          "email":"a@b.nl"
                        }""")
                .contentType(ContentType.JSON)
                .when()
                .post("/person")
                .then()
                .assertThat()
                .statusCode(200);

        Person actual =
                when().get("/person/1")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(Person.class);

        Person expected = Person.builder()
                .name("Mark")
                .age(36)
                .email("a@b.nl")
                .build();

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(expected);
    }
}
