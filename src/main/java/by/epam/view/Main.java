package by.epam.view;

import by.epam.entity.Article;
import by.epam.exception.ServiceException;
import by.epam.service.ArticleService;
import by.epam.service.ArticleServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        ArticleService service = new ArticleServiceImpl();

/*load all articles from the files*/
        List<Article> articles = service.getArticles();
        articles.stream().forEach(System.out::println);

/*persist all articles to db*/
        service.addArticles(articles);

       /* List<Author> authors = service.getAuthors();
        authors.stream().forEach(System.out::println);*/

    }
}
