package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(name)), Charset.forName(FILE_ENCODING)))) {
            ;
            String title = reader.readLine();
            String author = pullAuthorName(reader);
            String content = pullContent(reader);

            return new Article(title, author, content);
        } catch (FileNotFoundException e) {
            throw new DAOException("No such file:" + name, e);
        } catch (IOException e) {
            throw new DAOException("Input exception:", e);
        }
    }

    private String pullAuthorName(BufferedReader reader) throws DAOException, IOException {
        String authorString = reader.readLine();
        String[] tempArray = authorString.split(": ");
        if (tempArray.length == 2) {
            String author = tempArray[1].trim();
            return author;
        }
        throw new DAOException("Corrupted author format: " + authorString);
    }

    private String pullContent(BufferedReader reader) throws IOException {
        String content = "";

        String accumulator;
        while ((accumulator = reader.readLine()) != null) {
            content += accumulator;
        }
        return content;
    }
}
