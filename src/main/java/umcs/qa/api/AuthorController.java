package umcs.qa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umcs.qa.model.Author;
import umcs.qa.service.AuthorService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Author> getAuthors(){
        return authorService.getAll();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> addClient(@RequestBody Author authorData){
        Optional<Author> clientOptional = authorService.add(authorData);
        return clientOptional
                .map(author -> new ResponseEntity<>(author, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }



}
