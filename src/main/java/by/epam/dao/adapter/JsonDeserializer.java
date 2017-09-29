package by.epam.dao.adapter;

import by.epam.entity.Article;
import by.epam.entity.Author;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class JsonDeserializer extends StdDeserializer<Article> {

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    public JsonDeserializer() {
        this(Article.class);
    }


    protected JsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get("article");
        String title = articleNode.get("title").asText();
        String authorName = articleNode.get("author").asText();
        String contents = articleNode.get("contents").asText();

        if(authorName.isEmpty()){
            authorName = DEFAULT_AUTHOR_NAME;
        }
        return new Article(title, new Author(authorName), contents);
    }
}
