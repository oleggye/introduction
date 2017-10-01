package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleTxtDeserializer;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;

import java.io.*;
import java.nio.charset.Charset;

public class TxtParser extends AbstractParser {

    private static final String EXTENSION = "txt";


    public TxtParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String name) throws DAOException {

        ArticleTxtDeserializer deserializer = new ArticleTxtDeserializer();
        Article article = deserializer.deserialize(name);

        Author author = registerAuthorInLocalRepo(article.getAuthor());
        author.getArticles().add(article);
        article.setAuthor(author);

        return article;
    }
}
