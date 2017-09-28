package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class AbstractParser implements IParser {
    private static final String EXTENSION_FORMAT_PATTERN = "{*.%s}";
    private String extension;

    public AbstractParser(String extension) {
        this.extension = extension;
    }

    protected String[] getAllFileNamesFromDirectory(String directory, String extension) throws DAOException {
        String[] fileNames;
        String preparedExtension = prepareExtension(extension);

        try {
            Path path = Paths.get(directory);
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, preparedExtension)) {

                fileNames = StreamSupport
                        .stream(directoryStream.spliterator(), false)
                        .map(Path::toString).toArray(String[]::new);
            }
            return fileNames;
        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    private String prepareExtension(String extension) {
        return String.format(EXTENSION_FORMAT_PATTERN, extension);
    }

    public  List<Article> getArticles(String directory) throws DAOException{
        List<Article> articleList = new LinkedList<>();

        String[] names = getAllFileNamesFromDirectory(directory, extension);

        for (String name : names) {
            Article article = parse(name);
            articleList.add(article);
        }
        return articleList;
    }

    protected abstract Article parse(String name) throws DAOException;
}
