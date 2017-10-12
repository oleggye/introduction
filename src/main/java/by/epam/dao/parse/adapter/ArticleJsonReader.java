package by.epam.dao.parse.adapter;

import static org.slf4j.LoggerFactory.getLogger;

import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.entity.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public class ArticleJsonReader implements ArticleReader {

    private static final Logger LOGGER = getLogger(ArticleJsonReader.class);

    @Override
    public Article read(File file) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Article.class, new ArticleJsonDeserializer());
        mapper.registerModule(module);

        try {
            return mapper.readValue(file, Article.class);
        } catch (IOException e) {
            String errorMessage = "Input exception:";
            LOGGER.error(errorMessage, e);
            throw new ParseException("Input exception:", e);
        }
    }
}
