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
        return name + " - " + articles.size() + (articles.size() > 0 ? "\n" + articleTitles : "");
    }

    private String prepareArticleTitles() {
        return articles.stream()
                .map(Article::getTitle).map(s -> "\"" + s + "\"").reduce((s1, s2) -> s1 + "\n" + s2).orElse("");
    }
}
