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
    public ResponseEntity<Author> addAuthor(@RequestBody Author authorData){
        Optional<Author> authorOptional = authorService.add(authorData);
        return authorOptional
                .map(author -> new ResponseEntity<>(author, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getAuthorById(@PathVariable long id){
        Optional<Author> authorOptional = authorService.getById(id);
        return authorOptional
                .map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping(path="/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> updateAuthor(@PathVariable long id, @RequestBody Author authorData){
        authorData.setId(id);
        Optional <Author> authorOptional=  authorService.update(authorData);
        return authorOptional
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }



}
