package by.epam.entity;

public class ArticleBuilder {

    private String title;
    private Author author;
    private String contents;

    public ArticleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleBuilder setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public ArticleBuilder setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public Article build() {
        Article article = new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setContents(contents);

        return article;
    }
}
