package by.epam.dao.repository.impl;

import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl extends AbstractRepository<Article, String> implements ArticleRepository {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }
}
