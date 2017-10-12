package by.epam.dao.util;

import by.epam.entity.Author;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthorCache {

    private final Map<String, Author> repository = new ConcurrentHashMap<>();

    public Author cacheAuthor(Author author) {
        Author resultAuthor;

        if (!repository.containsKey(author.getName())) {
            repository.put(author.getName(), author);
            resultAuthor = author;
        } else {
            resultAuthor = repository.get(author.getName());
        }
        return resultAuthor;
    }
}
