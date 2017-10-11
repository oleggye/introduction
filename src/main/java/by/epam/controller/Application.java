package by.epam.controller;

import by.epam.entity.Article;
import by.epam.exception.ServiceException;
import by.epam.service.FileService;
import by.epam.service.StoreService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Application {

    public static void main(String[] args) throws ServiceException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springContext.xml");

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        FileService fileService = context.getBean(FileService.class);

/*load all articles from the files*/
        List<Article> articles = fileService.readArticles();
        articles.stream().forEach(System.out::println);

/*persist all articles to database*/
        StoreService storeService = context.getBean(StoreService.class);
        storeService.addArticles(articles);

    }
}
