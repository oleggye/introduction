package by.epam.dao.parse.impl;

import by.epam.dao.parse.Parser;
import by.epam.dao.exception.DAOException;
import by.epam.dao.util.AuthorRepository;
import by.epam.dao.util.FileResolver;
import by.epam.entity.Article;
import by.epam.entity.Author;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractParser implements Parser {

    private static final FileResolver resolver = new FileResolver();
    private static final AuthorRepository repository = new AuthorRepository();

    private String extension;

    protected AbstractParser(String extension) {
        this.extension = extension;
    }

    public List<Article> loadArticles(String directory) throws DAOException {
        List<Article> articleList = new LinkedList<>();

        String[] names = resolver.getFileNamesWithExtensionFromDirectory(directory, extension);

        for (String fileName : names) {
            Article article = loadArticle(new File(fileName));

            Article optimisedArticle = optimiseArticleReferences(article);
            articleList.add(optimisedArticle);
        }
        return articleList;
    }

    private Article optimiseArticleReferences(Article article) {
        Author author = repository.addAuthorToLocalRepository(article.getAuthor());
        author.getArticles().add(article);
        article.setAuthor(author);
        return article;
    }
}
