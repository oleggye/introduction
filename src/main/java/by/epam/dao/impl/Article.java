package by.epam.dao.impl;

import by.epam.dao.adapter.MyNormalizedStringAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


@XmlRootElement(name = "article")
public class Article implements Serializable {

    private String title;
    private String author;
    private String contents;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title", required = true)
    @XmlJavaTypeAdapter(MyNormalizedStringAdapter.class)
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "author", defaultValue = "Unknown")
    @XmlJavaTypeAdapter(MyNormalizedStringAdapter.class)
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    @XmlElement(name = "contents", required = true)
    @XmlJavaTypeAdapter(MyNormalizedStringAdapter.class)
    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
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