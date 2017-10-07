package by.epam.view;

import by.epam.entity.Article;
import by.epam.exception.ServiceException;
import by.epam.service.ArticleService;
import by.epam.service.ArticleServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException {
        ArticleService service = new ArticleServiceImpl();

        List<Article> articles = service.getArticles();
        //articles.stream().forEach(System.out::println);

        service.addArticles(articles);

       /*List<Author> authors = service.getAuthors();
       authors.stream().forEach(System.out::println);*/

    }
    /*private static final String PERSISTENCE_UNIT_NAME = "my-app";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select art from Article art");

        List<Article> articleList = q.getResultList();
        for (Article article : articleList) {
            System.out.println(article);
        }
        System.out.println("Size: " + articleList.size());
    }*/
}
