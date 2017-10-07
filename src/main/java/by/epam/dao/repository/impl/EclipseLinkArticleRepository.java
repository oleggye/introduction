package by.epam.dao.repository.impl;

import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;

import javax.persistence.EntityManager;

public class EclipseLinkArticleRepository extends CrudRepository<Article, String> implements ArticleRepository {

    public EclipseLinkArticleRepository(EntityManager manager) {
        super(Article.class, manager);
    }
}
