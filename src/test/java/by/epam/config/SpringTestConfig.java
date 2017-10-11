package by.epam.config;

import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

import static by.epam.dao.util.PropertyLoader.getInstance;

@Configuration
public class SpringTestConfig {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("testData");

    @Bean
    @Qualifier("firstArticle")
    public Article getFirstArticle() {
        final String title = bundle.getString("article.json.title.first");
        final String authorName = bundle.getString("article.json.authorName.first");
        final String contents = bundle.getString("article.json.contents.first");

        Author author = new Author(authorName);
        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }

    @Bean
    @Qualifier("secondArticle")
    public Article getSecondArticle() {
        final String title = bundle.getString("article.json.title.second");
        final String authorName = bundle.getString("article.json.authorName.second");
        final String contents = bundle.getString("article.json.contents.second");

        Author author = new Author(authorName);
        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }

    @Bean
    @Qualifier("thirdArticle")
    public Article getThirdArticle() {
        final String title = bundle.getString("article.json.title.third");
        final String authorName = bundle.getString("article.json.authorName.third");
        final String contents = bundle.getString("article.json.contents.third");

        Author author = new Author(authorName);
        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }
}
