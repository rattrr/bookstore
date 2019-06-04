package umcs.qa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umcs.qa.model.Article;
import umcs.qa.service.ArticleService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Article> getArticles(){
        return articleService.getAll();
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> addArticle(@RequestBody Article articleData){
        Optional<Article> articleOptional = articleService.add(articleData);
        return articleOptional
                .map(article -> new ResponseEntity<>(article, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/generate/{words}", produces = TEXT_PLAIN_VALUE)
    public String generateArticle(@PathVariable Long words){
        return articleService.generateContent(words);
    }
}
