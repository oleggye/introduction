package by.epam.view;

import by.epam.entity.Author;
import by.epam.exception.ServiceException;
import by.epam.service.ParseService;
import by.epam.service.ParseServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        ParseService service = new ParseServiceImpl();

       /* List<Article> articles = service.loadArticles();
        articles.stream().forEach(System.out::println);*/

       List<Author> authors = service.getAuthors();
       authors.stream().forEach(System.out::println);

    }
}
