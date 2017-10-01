package by.epam.dao.adapter;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;

import java.io.*;
import java.nio.charset.Charset;

public class ArticleTxtDeserializer {
    private static final String AUTHOR_SEPARATOR = "Written by: ";
    private static final String FILE_ENCODING = "utf-8";

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    public Article deserialize(String fileName) throws DAOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(fileName)), Charset.forName(FILE_ENCODING)))) {

            String line;
            String title = null;
            String content = null;
            Author author = new Author(DEFAULT_AUTHOR_NAME);
            boolean isAuthorNotFound = true;

            while ((line = reader.readLine()) != null) {

                if (line.contains(AUTHOR_SEPARATOR)) {
                    author = pullAuthor(line);
                    isAuthorNotFound = false;
                } else {
                    if (isAuthorNotFound) {
                        title += line;
                    } else {
                        content += line;
                    }
                }
            }
            Article article = new Article(title, author, content);

            return article;
        } catch (FileNotFoundException e) {
            throw new DAOException("No such file:" + fileName, e);
        } catch (IOException e) {
            throw new DAOException("Input exception:", e);
        }
    }

    private Author pullAuthor(String authorString) throws DAOException, IOException {
        String[] tempArray = authorString.split(AUTHOR_SEPARATOR);
        if (tempArray.length == 2) {
            String authorName = tempArray[1].trim();
            return new Author(authorName);
        }
        throw new DAOException("Corrupted author format: " + authorString);
    }
}
