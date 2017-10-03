package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TxtParserTest {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources/files";

    private IParser parser = new TxtParser();

    /*@Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOURCE_DIRECTORY_PATH);
        list.stream().forEach(System.out::println);
    }*/

    @Test
    public void testParseFirstFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\files\\Article7.txt";

        final String expectedTitle = "THE FEATURES AND BENEFITS OF ORACLE COHERENCE";
        final Author expectedAuthor = new Author("Jonathan Hult");
        final String expectedContents = "Coherence has many meanings across different domains, including physics, mathematics, or computer science. Coherence within computer science may refer to a feature within Parallels Desktop for Mac, a media server, or Oracle Coherence. This blog post is an introduction to Oracle Coherence. Oracle Coherence is an in-memory data grid and distributed caching solution. Coherence enables organizations to predictably scale mission-critical applications by providing fast access to frequently used data. It provides a robust data abstraction layer that brokers the supply and demand of data between applications and data sources. Coherence is composed of many individual nodes or JVMs which work together to provide highly reliable and high speed virtual caching. The complexity of the cluster is completely hidden from the user of the virtual cache. By automatically and dynamically partitioning data, Coherence ensures continuous data availability and transactional integrity, even in the event of a server failure. Simply stated, Oracle Coherence is a peer-to-peer, high availability data grid that supports Extreme Scaling, Increased Performance and Improved Reliability for applications and middleware. These factors are a crucial component to addressing the challenges faced by many applications today, which must grow and scale elastically, reduce back-end load on databases, applications and mainframes and operate in a vast landscape of the Cloud, Shared Services and custom applications all requiring low latency and reliable access to data.";

        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContents);

        Method parse = TxtParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void testParseSecondFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fileName = "src\\main\\resources\\files\\Article8.txt";

        final String expectedTitle = "Notes on Oracle Coherence.Coherense cache: fast & easy";
        final Author expectedAuthor = new Author("Ricky Ho");
        final String expectedContents = "Oracle Coherence is a distributed cache that functionally comparable to Memcached. On top of the basic cache API function, it has some additional capabilities that is attractive for building large scale enterprise applications. The API is based on a Java Map (Hashtable) Interface. It is based on a key/value store semantics where the value can be any Java Serializable object. Coherence allows multiple cache identified by a unique name (which they called a \"named cache\"). The common usage pattern is to locate a cache by its name, and then act on the cache.";
        final Article expectedArticle = new Article(expectedTitle, expectedAuthor, expectedContents);

        Method parse = TxtParser.class.getDeclaredMethod("parse", String.class);
        Article actualArticle = (Article) parse.invoke(parser, fileName);

        Assert.assertNotNull(actualArticle);
        Assert.assertEquals(expectedArticle, actualArticle);
        Assert.assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        Assert.assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        Assert.assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}
