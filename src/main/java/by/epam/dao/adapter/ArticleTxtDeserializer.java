package by.epam.dao.adapter;

import by.epam.dao.adapter.exception.ParseException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;

public class ArticleTxtDeserializer {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String AUTHOR_SEPARATOR = "Written by: ";
    private static final String FILE_ENCODING = "utf-8";

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    public Article deserialize(String fileName) throws ParseException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(fileName)), Charset.forName(FILE_ENCODING)))) {

            String line;
            String title = null;
            String contents = null;
            Author author = new Author(DEFAULT_AUTHOR_NAME);
            boolean isAuthorNotFound = true;

            while ((line = reader.readLine()) != null) {
                line = correctLine(line);

                if (line.contains(AUTHOR_SEPARATOR)) {
                    author = pullAuthor(line);
                    isAuthorNotFound = false;

                } else {

                    if (isAuthorNotFound) {
                        if (title == null)
                            title = "";
                        title += line;
                    } else {
                        if (contents == null)
                            contents = "";
                        contents += line;
                    }
                }
            }

            return new Article(title, author, contents);
        } catch (FileNotFoundException e) {
            String errorMessage = "No such file:" + fileName;
            LOGGER.error(errorMessage, e);
            throw new ParseException(errorMessage, e);

        } catch (IOException e) {
            String errorMessage = "Input exception:";
            LOGGER.error(errorMessage, e);
            throw new ParseException("Input exception:", e);
        }
    }

    private Author pullAuthor(String authorString) throws ParseException {
        String[] tempArray = authorString.split(AUTHOR_SEPARATOR);
        if (tempArray.length == 2) {
            String authorName = tempArray[1].trim();
            return new Author(authorName);
        }
        String errorMessage = "Corrupted author format: " + authorString;
        LOGGER.error(errorMessage);
        throw new ParseException(errorMessage);
    }

    private String correctLine(String line) {
        line = line.trim();
        line = line.replaceAll("\uFEFF", "");
        return line;
    }
}
