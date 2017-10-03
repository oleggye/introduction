package by.epam.dao.impl;

import static by.epam.dao.impl.AbstractParser.FileResolver.getFileNamesWithExtensionFromDirectory;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

public abstract class AbstractParser implements IParser {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AbstractParser.class);

    private static final ConcurrentHashMap<String, Author> localRepo = new ConcurrentHashMap<>();
    private static final String EXTENSION_FORMAT_PATTERN = "{*.%s}";
    private String extension;

    protected AbstractParser(String extension) {
        this.extension = extension;
    }

    public List<Article> getArticles(String directory) throws DAOException {
        List<Article> articleList = new LinkedList<>();

        String[] names = getFileNamesWithExtensionFromDirectory(directory, extension);

        for (String fileName : names) {
            Article article = parse(fileName);
            articleList.add(article);
        }
        return articleList;
    }

    protected abstract Article parse(String name) throws DAOException;

    protected Author registerAuthorInLocalRepo(Author author) {
        Author resultAuthor;

        if (!localRepo.containsKey(author.getName())) {
            localRepo.put(author.getName(), author);
            resultAuthor = author;
        } else {
            resultAuthor = localRepo.get(author.getName());
        }
        return resultAuthor;
    }

    static final class FileResolver {

        private FileResolver() {
        }

        protected static String[] getFileNamesWithExtensionFromDirectory(String directory, String extension)
            throws DAOException {
            String[] fileNames;
            String preparedExtension = prepareExtension(extension);

            try {
                Path path = Paths.get(directory);
                try (DirectoryStream<Path> directoryStream =
                         Files.newDirectoryStream(path, preparedExtension)) {

                    fileNames = StreamSupport
                        .stream(directoryStream.spliterator(), false)
                        .map(Path::toString).toArray(String[]::new);
                }
                return fileNames;
            } catch (IOException e) {
                String errorMessage = "Can't scan given directory: " + directory;
                LOGGER.warn(errorMessage, e);
                throw new DAOException(e.getMessage());
            }
        }

        private static String prepareExtension(String extension) {
            return String.format(EXTENSION_FORMAT_PATTERN, extension);
        }
    }
}
