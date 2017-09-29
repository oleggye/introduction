package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;

import java.io.*;
import java.nio.charset.Charset;

public class TxtParser extends AbstractParser {

    private static final String EXTENSION = "txt";
    private static final String FILE_ENCODING = "utf-8";

    public TxtParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String name) throws DAOException {
        try (BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(
                                                        new File(name)), Charset.forName(FILE_ENCODING)))) {

            String title = reader.readLine();
            Author author = pullAuthor(reader);
            String content = pullContent(reader);
            Article article = new Article(title, author, content);

            author = registerAuthorInLocalRepo(author);
            author.getArticles().add(article);
            article.setAuthor(author);

            return article;
        } catch (FileNotFoundException e) {
            throw new DAOException("No such file:" + name, e);
        } catch (IOException e) {
            throw new DAOException("Input exception:", e);
        }
    }

    private Author pullAuthor(BufferedReader reader) throws DAOException, IOException {
        String authorString = reader.readLine();
        String[] tempArray = authorString.split("Written by: ");
        if (tempArray.length == 2) {
            String authorName = tempArray[1].trim();
            return new Author(authorName);
        }
        throw new DAOException("Corrupted author format: " + authorString);
    }

    private String pullContent(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();

        String accumulator;
        while ((accumulator = reader.readLine()) != null) {
            content.append(accumulator);
        }
        return content.toString();
    }
}
