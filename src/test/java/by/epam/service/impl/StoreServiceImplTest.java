package by.epam.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import by.epam.dao.repository.ArticleRepository;
import by.epam.dao.repository.AuthorRepository;
import by.epam.entity.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest {

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addArticle() throws Exception {
        final Article testArticle = new Article();
        storeService.addArticle(testArticle);

        verify(articleRepository, times(1)).save(testArticle);
    }

    @Test
    public void addArticles() throws Exception {
        final List<Article> testArticles = Collections.EMPTY_LIST;
        storeService.addArticles(testArticles);

        verify(articleRepository, times(1)).saveAll(testArticles);
    }
}