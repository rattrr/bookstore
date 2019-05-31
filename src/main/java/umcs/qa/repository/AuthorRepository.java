package umcs.qa.repository;

import org.springframework.data.repository.CrudRepository;
import umcs.qa.model.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> getAllByFirstNameEqualsAndLastNameEqualsAndDateOfBirthEquals(String firstName, String lastName, LocalDate dateOfBirth);
}
