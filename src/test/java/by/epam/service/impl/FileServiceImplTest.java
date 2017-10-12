package by.epam.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.epam.dao.ParserFactory;
import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    @Mock
    private ParserFactory factory;
    @Mock
    private Parser parser;

    private static final Author firstAuthor = new Author("Author1");
    private static final Article firstArticle =
        new ArticleBuilder()
            .setTitle("Title1")
            .setAuthor(firstAuthor)
            .setContents("Contents1")
            .build();

    private static final Author secondAuthor = new Author("Author2");
    private static final Article secondArticle =
        new ArticleBuilder()
            .setTitle("Title2")
            .setAuthor(secondAuthor)
            .setContents("Contents2")
            .build();

    private static final Author thirdAuthor = new Author("Author3");
    private static final Article thirdArticle =
        new ArticleBuilder()
            .setTitle("Title3")
            .setAuthor(thirdAuthor)
            .setContents("Contents3")
            .build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReadEmptyArticles() throws Exception {
        final List<Article> expectedArticles = Collections.EMPTY_LIST;

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(expectedArticles);

        List<Article> actualArticles = fileService.readArticles();

        assertNotNull(actualArticles);
        assertEquals(expectedArticles, actualArticles);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadArticlesWithTripleRepetitionOfFirstArticle() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(firstArticle);
        final List<Article> expectedArticles = Arrays.asList(firstArticle, firstArticle, firstArticle);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Article> actualArticles = fileService.readArticles();

        assertNotNull(actualArticles);
        assertEquals(expectedArticles, actualArticles);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadArticlesWithTripleRepetitionOfBothFirstAndSecondArticles() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(firstArticle, secondArticle);
        final List<Article>
            expectedArticles =
            Arrays.asList(firstArticle, secondArticle,
                          firstArticle, secondArticle,
                          firstArticle, secondArticle);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Article> actualArticles = fileService.readArticles();

        assertNotNull(actualArticles);
        assertEquals(expectedArticles, actualArticles);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadArticlesWithTripleRepetitionOfFirstSecondAndThirdArticles() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(firstArticle, secondArticle, thirdArticle);
        final List<Article>
            expectedArticles =
            Arrays.asList(firstArticle, secondArticle, thirdArticle,
                          firstArticle, secondArticle, thirdArticle,
                          firstArticle, secondArticle, thirdArticle);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Article> actualArticles = fileService.readArticles();

        assertNotNull(actualArticles);
        assertEquals(expectedArticles, actualArticles);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadEmptyAuthors() throws Exception {
        final List<Article> parserReturnValue = Collections.EMPTY_LIST;
        final List<Author> expectedAuthors = Collections.EMPTY_LIST;

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Author> actualAuthor = fileService.readAuthors();

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthors, actualAuthor);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadFirstArticleAuthor() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(firstArticle);
        final List<Author> expectedAuthors = Arrays.asList(firstAuthor);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Author> actualAuthor = fileService.readAuthors();

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthors, actualAuthor);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadSecondArticleAuthor() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(secondArticle);
        final List<Author> expectedAuthors = Arrays.asList(secondAuthor);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Author> actualAuthor = fileService.readAuthors();

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthors, actualAuthor);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }

    @Test
    public void shouldReadThirdArticleAuthor() throws Exception {
        final List<Article> parserReturnValue = Arrays.asList(thirdArticle);
        final List<Author> expectedAuthors = Arrays.asList(thirdAuthor);

        when(factory.getParser(any(ParserType.class))).thenReturn(parser);
        when(parser.loadArticles(anyString())).thenReturn(parserReturnValue);

        List<Author> actualAuthor = fileService.readAuthors();

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthors, actualAuthor);

        verify(factory, times(3)).getParser(any(ParserType.class));
        verifyNoMoreInteractions(factory);

        verify(parser, times(3)).loadArticles(anyString());
    }


}