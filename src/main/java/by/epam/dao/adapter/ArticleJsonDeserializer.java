package by.epam.dao.adapter;

import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ArticleJsonDeserializer extends JsonDeserializer<Article> {

    private final String articleTagName;
    private final String titleTagName;
    private final String authorTagName;
    private final String contentsTagName;

    private final String defaultAuthorName;

    protected ArticleJsonDeserializer() {
        articleTagName = PropertyLoader.getInstance().getString("reader.json.articleTagName");
        defaultAuthorName = PropertyLoader.getInstance().getString("reader.default.authorName");
        titleTagName = PropertyLoader.getInstance().getString("reader.json.titleName");
        authorTagName = PropertyLoader.getInstance().getString("reader.json.authorName");
        contentsTagName = PropertyLoader.getInstance().getString("reader.json.contentsName");
    }

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get(articleTagName);

        String title = safetyGetElement(articleNode.get(titleTagName));
        String authorName = safetyGetElement(articleNode.get(authorTagName));
        String contents = safetyGetElement(articleNode.get(contentsTagName));

        if (authorName == null) {
            authorName = defaultAuthorName;
        }
        Author author = new Author(authorName);
        return new ArticleBuilder()
            .setTitle(title)
            .setAuthor(author)
            .setContents(contents).build();
    }

    private String safetyGetElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        } else {
            return jsonNode.asText().trim();
        }
    }
}
