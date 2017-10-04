package by.epam.dao.adapter;

import by.epam.dao.adapter.exception.ParseException;
import by.epam.entity.Article;

import java.io.File;

public interface ArticleReader {

    Article load(File file) throws ParseException;
}
