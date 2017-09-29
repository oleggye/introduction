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
    private static final String DEFAULT_SAFETY_VALUE = "";

    private static final String ARTICLE_NODE_TAG = "article";
    private static final String TITLE_NODE_TAG = "title";
    private static final String AUTHOR_NODE_TAG = "author";
    private static final String CONTENTS_NODE_TAG = "contents";

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get(ARTICLE_NODE_TAG);

        String title = safetyGetElement(articleNode.get(TITLE_NODE_TAG));
        String authorName = safetyGetElement(articleNode.get(AUTHOR_NODE_TAG));
        String contents = safetyGetElement(articleNode.get(CONTENTS_NODE_TAG));

        if (authorName.isEmpty()) {
            authorName = DEFAULT_AUTHOR_NAME;
        }
        return new Article(title, new Author(authorName), contents);
    }

    private String safetyGetElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return DEFAULT_SAFETY_VALUE;
        } else {
            return jsonNode.asText();
        }
    }
}
