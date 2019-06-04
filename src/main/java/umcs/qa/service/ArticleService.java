package umcs.qa.service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umcs.qa.model.Article;
import umcs.qa.repository.ArticleRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;


    @Autowired
    ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> add(Article article) {
        if (notExists(article)) {
            return Optional.of(articleRepository.save(article));
        }
        return Optional.empty();
    }


    public List<Article> getAll() {
        Iterable<Article> articleIterable = articleRepository.findAll();
        return StreamSupport.stream(articleIterable.spliterator(), false).collect(Collectors.toList());
    }

    private boolean notExists(Article article) {
        return articleRepository.getAllByAuthorIdEqualsAndTitleEquals(article.getAuthorId(), article.getTitle()).isEmpty();
    }

    public String generateContent(Long length) {
        return LoremIpsum.getInstance().getWords( Math.toIntExact(length));

    }

    private boolean existsArticleWithId(long id) {
        return articleRepository.findById(id).isPresent();
    }


}

