package by.epam.dao.repository.impl;

import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;

public class EclipseLinkArticleRepository extends CrudRepository<Article, String> implements ArticleRepository {

    public EclipseLinkArticleRepository() {
        super(Article.class);
    }
}
