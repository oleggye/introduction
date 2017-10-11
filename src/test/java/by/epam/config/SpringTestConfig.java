package by.epam.config;

import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {

    private static final PropertyLoader loader = PropertyLoader.getInstance();

    @Bean
    @Qualifier("firstArticle")
    public Article getFirstArticle() {
        final String authorName = loader.getString("article.json.authorName.first");
        Author author = new Author(authorName);
        return new ArticleBuilder()
            .setTitle(loader.getString("article.json.title.first"))
            .setAuthor(author)
            .setContents(loader.getString("article.json.contents.first"))
            .build();
    }

    @Bean
    @Qualifier("secondArticle")
    public Article getSecondArticle() {
        final String authorName = loader.getString("article.json.authorName.second");
        Author author = new Author(authorName);
        return new ArticleBuilder()
            .setTitle(loader.getString("article.json.title.second"))
            .setAuthor(author)
            .setContents(loader.getString("article.json.contents.second"))
            .build();
    }

    @Bean
    @Qualifier("thirdArticle")
    public Article getThirdArticle() {
        final String authorName = loader.getString("article.json.authorName.third");
        Author author = new Author(authorName);
        return new ArticleBuilder()
            .setTitle(loader.getString("article.json.title.third"))
            .setAuthor(author)
            .setContents(loader.getString("article.json.contents.third"))
            .build();
    }

}
