package by.epam.dao.util;

import by.epam.dao.exception.DAOException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.StreamSupport;

public class FileResolver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FileResolver.class);
    private static final String EXTENSION_FORMAT_PATTERN = "{*.%s}";

    public String[] getFileNamesWithExtensionFromDirectory(String directory, String extension)
        throws DAOException {
        Path[] paths = getFilePathWithExtensionFromDirectory(directory, extension);
        return Arrays.stream(paths).map(Path::toString).toArray(String[]::new);
    }


    public Path[] getFilePathWithExtensionFromDirectory(String directory, String extension)
        throws DAOException {
        Path[] fileNames;
        String preparedExtension = prepareExtension(extension);

        Path path = Paths.get(directory);

        try (DirectoryStream<Path> directoryStream =
                 Files.newDirectoryStream(path, preparedExtension)) {

            fileNames = StreamSupport
                .stream(directoryStream.spliterator(), false)
                .toArray(Path[]::new);

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
