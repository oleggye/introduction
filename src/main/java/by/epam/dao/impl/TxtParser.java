package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.util.Collections;
import java.util.List;

public class TxtParser extends AbstractParser{

    private static final String EXTENSION = "txt";

    public TxtParser() {
        super(EXTENSION);
    }

    @Override
    protected List<Article> parse(String name) throws DAOException {
        return null;
    }
}
