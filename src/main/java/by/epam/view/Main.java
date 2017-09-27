package by.epam.view;

import by.epam.entity.Article;
import by.epam.service.IParseService;
import by.epam.service.ParseService;
import by.epam.exception.ServiceException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        IParseService service = new ParseService();

        List<Article> articles = service.getArticles();
        System.out.println(articles);

    }
}
