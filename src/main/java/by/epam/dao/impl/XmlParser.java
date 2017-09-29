package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

public class XmlParser extends AbstractParser {
    private static final String EXTENSION = "xml";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public XmlParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String fileName) throws DAOException {
        try {
            /*JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Article article = (Article) unmarshaller.unmarshal(new File(fileName));*/

            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader sr = f.createXMLStreamReader(new FileInputStream(fileName));

            XmlMapper mapper = new XmlMapper();
            //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            sr.next();
            sr.next();
            Article article = mapper.readValue(sr, Article.class);

            Author author = registerAuthorInLocalRepo(article.getAuthor());
            article.setAuthor(author);
            author.getArticles().add(article);


           return article;
        }/* catch (JAXBException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }*/ catch (IOException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        } catch (XMLStreamException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
