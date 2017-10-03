package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XmlParserTest {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources/files";

    private IParser parser = new XmlParser();

    /*@Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOURCE_DIRECTORY_PATH);
        System.out.println("---Articles----");
        list.stream().forEach(System.out::println);

        System.out.println("---Authors----");
        list.stream().map(Article::getAuthor).peek(System.out::println).forEach(e->System.out.println(e.getArticles()));
    }*/

    @Test
    public void testParseFirstFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\files\\Article2.xml";

        final String expectedTitle = "The Java Platform module system";
        final Author expectedAuthor = new Author("Sander Mak");
        final String expectedContents = "The defining feature for Java 9 is an all-new module system. When codebases grow larger, the odds of creating complicated, tangled “spaghetti code” increase exponentially. There are two fundamental problems: It is hard to truly encapsulate code, and there is no notion of explicit dependencies between different parts (JAR files) of a system. Every public class can be accessed by any other public class on the classpath, leading to inadvertent usage of classes that weren't meant to be public API. Furthermore, the classpath itself is problematic: How do you know whether all the required JARs are there, or if there are duplicate entries? The module system addresses both issues.Modular JAR files contain an additional module descriptor. In this module descriptor, dependencies on other modules are expressed through`requires` statements. Additionally, `exports` statements control which packages are accessible to other modules. All non-exported packages are encapsulated in the module by default.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContents);

        Method parse = XmlParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void testParseSecondFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\files\\Article3.xml";

        final String expectedTitle = "Spring Framework - Overview";
        final Author expectedAuthor = new Author("Unknown");
        final String expectedContents = "Spring is the most popular application development framework for enterprise Java. Millions of developers around the world use Spring Framework to create high performing, easily testable, and reusable code. Spring framework is an open source Java platform. It was initially written by Rod Johnson and was first released under the Apache 2.0 license in June 2003. Spring is lightweight when it comes to size and transparency. The basic version of Spring framework is around 2MB. The core features of the Spring Framework can be used in developing any Java application, but there are extensions for building web applications on top of the Java EE platform. Spring framework targets to make J2EE development easier to use and promotes good programming practices by enabling a POJO-based programming model.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContents);

        Method parse = XmlParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    //FIXME: InvocationTargetException is throw as it covers DAOException by reflection API
    @Test(expected = InvocationTargetException.class)
    public void testParseThirdFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\files\\Article5.xml";

        final String expectedTitle = "Spring Framework - Overview";
        final Author expectedAuthor = new Author("Thorben Janssen");
        final String expectedContents = "Fetching multiple entities by their ID is a very common use case. Most developers either implement it with a loop that calls the find method of the EntityManager for each primary key or with a JPQL query that checks all primary key values in an IN clause. The first option requires Hibernate to perform a database query for each primary key. That can create huge performance issues. The second one allows you to fetch all entities with one query and is obviously the better option. Hibernate 5.1 introduced a third option that avoids the issues of the first and is easier to use than the second one. The new MultiIdentifierLoadAccess interface provides a comfortable option to load multiple entities with one query. You just need to call the byMultipleIds method on the Hibernate Session to get a MultiIdentifierLoadAccess interface and provide a list of primary key values to the multiLoad method.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContents);

        Method parse = XmlParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}
