package by.epam.dao.adapter;

import by.epam.entity.Author;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AuthorAdapter extends XmlAdapter<String, Author> {
    @Override
    public Author unmarshal(String v) throws Exception {
        return new Author(v.trim());
    }

    @Override
    public String marshal(Author v) throws Exception {
        return v.getName();
    }
}
