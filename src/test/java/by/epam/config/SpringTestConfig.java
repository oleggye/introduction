package by.epam.config;

import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

@Configuration
public class SpringTestConfig {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("testData");

    @Bean
    @Qualifier("firstAuthor")
    public Author getFirstAuthor() {
        final String authorName = bundle.getString("article.authorName.json.first");
        return new Author(authorName);
    }

    @Bean
    @Qualifier("firstArticle")
    public Article getFirstArticle() {
        final String title = bundle.getString("article.title.json.first");
        final Author author = getFirstAuthor();
        final String contents = bundle.getString("article.contents.json.first");

        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }

    @Bean
    @Qualifier("secondAuthor")
    public Author getSecondAuthor() {
        final String authorName = bundle.getString("article.authorName.json.second");
        return new Author(authorName);
    }

    @Bean
    @Qualifier("secondArticle")
    public Article getSecondArticle() {
        final String title = bundle.getString("article.title.json.second");
        final Author author = getSecondAuthor();
        final String contents = bundle.getString("article.contents.json.second");

        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }

    @Bean
    @Qualifier("thirdAuthor")
    public Author getThirdAuthor() {
        final String authorName = bundle.getString("article.authorName.json.third");
        return new Author(authorName);
    }

    @Bean
    @Qualifier("thirdArticle")
    public Article getThirdArticle() {
        final String title = bundle.getString("article.title.json.third");
        final Author author = getSecondAuthor();
        final String contents = bundle.getString("article.contents.json.third");

        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }
}
