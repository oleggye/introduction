package by.epam.dao.repository.impl;

import by.epam.dao.repository.AuthorRepository;
import by.epam.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl extends AbstractRepository<Author, String> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
