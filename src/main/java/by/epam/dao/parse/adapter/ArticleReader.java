package by.epam.dao.parse.adapter;

import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.entity.Article;

import java.io.File;

public interface ArticleReader {

    Article load(File file) throws ParseException;
}
