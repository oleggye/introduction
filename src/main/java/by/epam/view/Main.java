package by.epam.view;

import by.epam.entity.Article;
import by.epam.exception.ServiceException;
import by.epam.service.ArticleService;
import by.epam.service.ArticleServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        ArticleService service = new ArticleServiceImpl();

        List<Article> articles = service.getArticles();
        //articles.stream().forEach(System.out::println);

        service.addArticles(articles);

       /*List<Author> authors = service.getAuthors();
       authors.stream().forEach(System.out::println);*/

    }
}
