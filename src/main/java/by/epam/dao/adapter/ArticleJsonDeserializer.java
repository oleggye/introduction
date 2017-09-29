package by.epam.dao.adapter;

import by.epam.entity.Article;
import by.epam.entity.Author;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ArticleJsonDeserializer extends JsonDeserializer<Article> {

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    private static final String ARTICLE_NODE_TAG = "article";
    private static final String TITLE_NODE_TAG = "title";
    private static final String AUTHOR_NODE_TAG = "author";
    private static final String CONTENTS_NODE_TAG = "contents";

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get(ARTICLE_NODE_TAG);

        String title = articleNode.get(TITLE_NODE_TAG).asText();
        String authorName = articleNode.get(AUTHOR_NODE_TAG).asText();
        String contents = articleNode.get(CONTENTS_NODE_TAG).asText();

        if (authorName.isEmpty()) {
            authorName = DEFAULT_AUTHOR_NAME;
        }
        return new Article(title, new Author(authorName), contents);
    }
}
