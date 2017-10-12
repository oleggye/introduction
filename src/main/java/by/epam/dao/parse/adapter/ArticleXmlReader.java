package by.epam.dao.parse.adapter;

import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ArticleXmlReader implements ArticleReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleXmlReader.class);

    private final String defaultAuthorName;
    private final String titleTagName;
    private final String authorTagName;
    private final String contentsTagName;

    public ArticleXmlReader() {
        defaultAuthorName = PropertyLoader.getInstance().getString("reader.default.authorName");
        titleTagName = PropertyLoader.getInstance().getString("reader.xml.titleName");
        authorTagName = PropertyLoader.getInstance().getString("reader.xml.authorName");
        contentsTagName = PropertyLoader.getInstance().getString("reader.xml.contentsName");
    }

    public Article read(File file) throws ParseException {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();
            Element documentElement = document.getDocumentElement();

            return buildArticle(documentElement.getChildNodes());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            LOGGER.error("File parsing error: " + file, e);
            throw new ParseException(e.getMessage(), e);
        }
    }

    private Article buildArticle(NodeList nodeList) {
        Map<String, String> nodeFieldMap = new HashMap<>();
        nodeFieldMap.put(authorTagName, defaultAuthorName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                String nodeName = node.getNodeName();
                String nodeValue = node.getTextContent().trim();

                nodeFieldMap.put(nodeName, nodeValue);
            }
        }
        String title = nodeFieldMap.get(titleTagName);
        String authorName = nodeFieldMap.get(authorTagName);
        String contents = nodeFieldMap.get(contentsTagName);

        Author author = new Author(authorName);
        return new ArticleBuilder()
            .setTitle(title)
            .setAuthor(author)
            .setContents(contents).build();
    }
}
