package by.epam.entity;

import java.util.HashSet;
import java.util.Set;

public class Author {

    private String name;
    private Set<Article> articles = new HashSet<>();

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        String articleTitles = prepareArticleTitles();
        return name + " - " + articles.size() + (articles.isEmpty() ? "\n" + articleTitles : "");
    }

    private String prepareArticleTitles() {
        return articles.stream()
                .map(Article::getTitle).map(s -> "\"" + s + "\"").reduce((s1, s2) -> s1 + "\n" + s2).orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
