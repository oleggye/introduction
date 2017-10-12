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
    @Qualifier("firstArticle")
    public Article getFirstArticle() {
        final String title = bundle.getString("article.title.json.first");
        final String authorName = bundle.getString("article.authorName.json.first");
        final String contents = bundle.getString("article.contents.json.first");

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
        final String title = bundle.getString("article.title.json.second");
        final String authorName = bundle.getString("article.authorName.json.second");
        final String contents = bundle.getString("article.contents.json.second");

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
        final String title = bundle.getString("article.title.json.third");
        final String authorName = bundle.getString("article.authorName.json.third");
        final String contents = bundle.getString("article.contents.json.third");

        Author author = new Author(authorName);
        return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents)
                .build();
    }
}
