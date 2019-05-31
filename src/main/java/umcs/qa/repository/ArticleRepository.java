package umcs.qa.repository;

import org.springframework.data.repository.CrudRepository;
import umcs.qa.model.Article;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> getAllByAuthorIdEqualsAndTitleEquals(long authorId, String title);
}
