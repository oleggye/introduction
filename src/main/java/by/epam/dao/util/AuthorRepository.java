package by.epam.dao.util;

import by.epam.entity.Author;

import java.util.concurrent.ConcurrentHashMap;

public class AuthorRepository {

    private final ConcurrentHashMap<String, Author> repository = new ConcurrentHashMap<>();

    public Author addAuthorToLocalRepository(Author author) {
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
