package by.epam.view;

import by.epam.entity.Author;
import by.epam.exception.ServiceException;
import by.epam.service.IParseService;
import by.epam.service.ParseService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        IParseService service = new ParseService();

       /* List<Article> articles = service.getArticles();
        articles.stream().forEach(System.out::println);*/

       List<Author> authors = service.getAuthors();
       authors.stream().forEach(System.out::println);

    }
}
