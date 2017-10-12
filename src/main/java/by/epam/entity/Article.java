package by.epam.entity;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_author")
    private Author author;

    @Column(nullable = false,length = 3_000)
    private String contents;

    public Article() {
    }

    public Article(String title, Author author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author.getName() + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        return contents != null ? contents.equals(article.contents) : article.contents == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
