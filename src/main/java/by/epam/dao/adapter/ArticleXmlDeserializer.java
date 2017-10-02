package by.epam.dao.adapter;

import by.epam.dao.adapter.exception.ParseException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ArticleXmlDeserializer {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String TITLE_TAG_NAME = "title";
    private static final String AUTHOR_TAG_NAME = "author";
    private static final String CONTENTS_TAG_NAME = "contents";

    private static final String DEFAULT_AUTHOR_NAME = "Unknown";

    public Article deserialize(String fileName) throws ParseException {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);

            document.getDocumentElement().normalize();
            Element documentElement = document.getDocumentElement();

            return buildArticle(documentElement.getChildNodes());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            LOGGER.error(e);
            throw new ParseException(e.getMessage(), e);
        }
    }

    private Article buildArticle(NodeList nodeList) throws ParseException {
        String title = null;
        String authorName = DEFAULT_AUTHOR_NAME;
        String contents = null;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                switch (node.getNodeName()) {
                    case TITLE_TAG_NAME:
                        title = node.getTextContent().trim();
                        break;
                    case AUTHOR_TAG_NAME:
                        authorName = node.getTextContent().trim();
                        break;
                    case CONTENTS_TAG_NAME:
                        contents = node.getTextContent().trim();
                        break;
                    default:
                        //never throw if file structure wasn't change
                        String errorMessage = "wrong argument: " + node.getNodeName();
                        LOGGER.error(errorMessage);
                        throw new ParseException(errorMessage);
                }
            }
        }
        Author author = new Author(authorName);
        return new Article(title, author, contents);
    }
}
