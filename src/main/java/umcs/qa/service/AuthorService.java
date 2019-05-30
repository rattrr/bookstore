package umcs.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umcs.qa.model.Author;
import umcs.qa.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll(){
        Iterable<Author> authorIterable = authorRepository.findAll();
        return StreamSupport.stream(authorIterable.spliterator(), false).collect(Collectors.toList());
    }

    public Optional<Author> getById(long id){
        return authorRepository.findById(id);
    }


    public Optional<Author> add(Author author){
        if(notExists(author)){
            return Optional.of(authorRepository.save(author));
        }
        return Optional.empty();
    }

    public Optional<Author> update(Author author){
        if(existsAuthorWithId(author.getId())){
            return Optional.of(authorRepository.save(author));
        }
        return Optional.empty();
    }

    private boolean notExists(Author author){
        return authorRepository.getAllByFirstNameEqualsAndLastNameEqualsAndDateOfBirthEquals(
                author.getFirstName(),
                author.getLastName(),
                author.getDateOfBirth())
                .isEmpty();
    }

    private boolean existsAuthorWithId(long id){
        return getById(id).isPresent();
    }
}
