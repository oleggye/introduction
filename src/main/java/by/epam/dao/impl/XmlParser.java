package by.epam.dao.impl;

import by.epam.entity.Article;

import java.util.Collections;
import java.util.List;

public class XmlParser extends AbstractParser {
    private static final String EXTENSION = "xml";

    public XmlParser() {
        super(EXTENSION);
    }

    @Override
    protected List<Article> parse(String name) {
        return null;
    }
}
