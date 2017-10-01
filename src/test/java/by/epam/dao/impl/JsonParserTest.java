package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonParserTest {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";

    private IParser parser = new JsonParser();

    @Test
    public void testParseFirstFile() throws DAOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\Article1.json";

        final String expectedTitle = "What are the most exciting features that are expected to be released in Java 9";
        final Author expectedAuthor = new Author("Alex Zhitnitsky");
        final String expectedContent = "Don’t get distracted by the relative silence around Java 9! The JDK committers are hard at work preparing the next release, which is expected to be generally available in September 2017. Early access builds are already out in the wild and we’re counting down the days for general availability on the Java 9 Countdown website. Today we have a pretty clear picture of the features we can expect in Java 9. If Java 8 could be described as the major release of lambdas, streams and API changes, then Java 9 is all about Jigsaw, jshell, and a collection of under the hood and API updates. In this post we’ve gathered some of the features we believe are the most exciting ones that are targeting Java 9 – Apart from the usual suspect, project Jigsaw, which took on the mission of breaking down the JRE and bringing modularity to Java’s core components.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContent);

        Method parse = JsonParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());

        /*List<Article> list = parser.getArticles(RESOURCE_DIRECTORY_PATH);
        list.stream().forEach(System.out::println);*/
    }

    @Test
    public void testParseSecondFile() throws DAOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\Article4.json";

        final String expectedTitle = "Dependency Injection and Inversion of Control";
        final Author expectedAuthor = new Author("Unknown");
        final String expectedContent = "A Java application — a loose term that runs the gamut from constrained, embedded applications to n-tier, server-side enterprise applications — typically consists of objects that collaborate to form the application proper. Thus the objects in an application have dependencies on each other. Although the Java platform provides a wealth of application development functionality, it lacks the means to organize the basic building blocks into a coherent whole, leaving that task to architects and developers. Although you can use design patterns such as Factory, Abstract Factory, Builder, Decorator, and Service Locator to compose the various classes and object instances that make up an application, these patterns are simply that: best practices given a name, with a description of what the pattern does, where to apply it, the problems it addresses, and so forth. Patterns are formalized best practices that you must implement yourself in your application. The Spring Framework Inversion of Control (IoC) component addresses this concern by providing a formalized means of composing disparate components into a fully working application ready for use. The Spring Framework codifies formalized design patterns as first-class objects that you can integrate into your own application(s). Numerous organizations and institutions use the Spring Framework in this manner to engineer robust, maintainable applications.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContent);

        Method parse = JsonParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void testParseThirdFile() throws DAOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\Article6.json";

        final String expectedTitle = "Hibernate ORM 5.2.11.Final User Guide: Introduction";
        final Author expectedAuthor = new Author("");
        final String expectedContent = "Working with both Object-Oriented software and Relational Databases can be cumbersome and time consuming. Development costs are significantly higher due to a paradigm mismatch between how data is represented in objects versus relational databases. Hibernate is an Object/Relational Mapping solution for Java environments. The term Object/Relational Mapping refers to the technique of mapping data from an object model representation to a relational data model representation (and visa versa).Hibernate not only takes care of the mapping from Java classes to database tables (and from Java data types to SQL data types), but also provides data query and retrieval facilities. It can significantly reduce development time otherwise spent with manual data handling in SQL and JDBC. Hibernate’s design goal is to relieve the developer from 95% of common data persistence-related programming tasks by eliminating the need for manual, hand-crafted data processing using SQL and JDBC. However, unlike many other persistence solutions, Hibernate does not hide the power of SQL from you and guarantees that your investment in relational technology and knowledge is as valid as always. Hibernate may not be the best solution for data-centric applications that only use stored-procedures to implement the business logic in the database, it is most useful with object-oriented domain models and business logic in the Java-based middle-tier. However, Hibernate can certainly help you to remove or encapsulate vendor-specific SQL code and will help with the common task of result set translation from a tabular representation to a graph of objects.";
        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContent);

        Method parse = JsonParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}
