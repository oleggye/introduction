package by.epam.dao.parse.adapter;

import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

public class ArticleTxtReader implements ArticleReader {

    private final String defaultAuthorName;

    public ArticleTxtReader() {
        defaultAuthorName = PropertyLoader.getInstance().getString("reader.default.authorName");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleTxtReader.class);

    private static final String AUTHOR_SEPARATOR = "Written by: ";
    private static final String FILE_ENCODING = "utf-8";

    public Article load(File file) throws ParseException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(file), Charset.forName(FILE_ENCODING)))) {

            String line;
            String title = null;
            String contents = null;
            Author author = new Author(defaultAuthorName);
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

            return new ArticleBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContents(contents).build();

        } catch (FileNotFoundException e) {
            String errorMessage = "No such file:" + file;
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
        String trimmedLine = line.trim();
        return trimmedLine.replaceAll("\uFEFF", "");
    }
}
