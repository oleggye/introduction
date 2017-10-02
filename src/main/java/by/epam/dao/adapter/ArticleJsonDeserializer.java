package by.epam.dao.adapter;

import by.epam.entity.Article;
import by.epam.entity.Author;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ArticleJsonDeserializer extends JsonDeserializer<Article> {
    private static final String ARTICLE_TAG_NAME = "article";
    private static final String TITLE_TAG_NAME = "title";
    private static final String AUTHOR_TAG_NAME = "author_name";
    private static final String CONTENTS_TAG_NAME = "content";

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get(ARTICLE_TAG_NAME);

        String title = safetyGetElement(articleNode.get(TITLE_TAG_NAME));
        String authorName = safetyGetElement(articleNode.get(AUTHOR_TAG_NAME));
        String contents = safetyGetElement(articleNode.get(CONTENTS_TAG_NAME));

        if (authorName == null) {
            authorName = DEFAULT_AUTHOR_NAME;
        }
        return new Article(title, new Author(authorName), contents);
    }

    private String safetyGetElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        } else {
            return jsonNode.asText().trim();
        }
    }
}
