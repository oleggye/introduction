package by.epam.dao.util;

import org.junit.Assert;
import org.junit.Test;

public class PropertyLoaderTest {

    private final PropertyLoader loader = PropertyLoader.getInstance();

    private void getTagValueAndVerify(String tagPropertyKey, String expectedTagValue){
        String actualArticleTagValue = loader.getString(tagPropertyKey);

        Assert.assertNotNull(actualArticleTagValue);
        Assert.assertEquals(expectedTagValue, actualArticleTagValue);
    }

    @Test
    public void shouldGetArticleTagValueForJsonString() throws Exception {
        final String articleTagPropertyKey = "reader.json.articleTagName";
        final String expectedArticleTagValue = "article";

        getTagValueAndVerify(articleTagPropertyKey,expectedArticleTagValue);
    }

    @Test
    public void shouldGetDefaultAuthorNameString() throws Exception {
        final String propertyKey = "reader.defaultAuthorName";
        final String expectedArticleTagValue = "Unknown";

        getTagValueAndVerify(propertyKey,expectedArticleTagValue);
    }

    @Test
    public void shouldGetTitleNameTagValueForJsonString() throws Exception {
        final String titleNameTagPropertyKey = "reader.json.titleName";
        final String expectedTitleTagValue = "title";

        getTagValueAndVerify(titleNameTagPropertyKey,expectedTitleTagValue);
    }

    @Test
    public void shouldGetTitleNameTagValueForXmlString() throws Exception {
        final String titleNameTagPropertyKey = "reader.xml.titleName";
        final String expectedTitleTagValue = "title";

        getTagValueAndVerify(titleNameTagPropertyKey,expectedTitleTagValue);
    }

    @Test
    public void shouldGetTitleNameTagValueForTxtString() throws Exception {
        final String titleNameTagPropertyKey = "reader.txt.titleName";
        final String expectedTitleTagValue = "title";

        getTagValueAndVerify(titleNameTagPropertyKey,expectedTitleTagValue);
    }


    @Test
    public void shouldGetAuthorNameTagValueForJsonString() throws Exception {
        final String authorNameTagPropertyKey = "reader.json.authorName";
        final String expectedAuthorTagValue = "author_name";

        getTagValueAndVerify(authorNameTagPropertyKey,expectedAuthorTagValue);
    }

    @Test
    public void shouldGetAuthorNameTagValueForXmlString() throws Exception {
        final String authorNameTagPropertyKey = "reader.xml.authorName";
        final String expectedAuthorTagValue = "author";

        getTagValueAndVerify(authorNameTagPropertyKey,expectedAuthorTagValue);
    }

    @Test
    public void shouldGetAuthorNameTagValueForTxtString() throws Exception {
        final String authorNameTagPropertyKey = "reader.txt.authorName";
        final String expectedAuthorTagValue = "author";

        getTagValueAndVerify(authorNameTagPropertyKey,expectedAuthorTagValue);
    }

    @Test
    public void shouldGetContentsTagValueForJsonString() throws Exception {
        final String contentsTagPropertyKey = "reader.json.contentsName";
        final String expectedArticleTagValue = "content";

        getTagValueAndVerify(contentsTagPropertyKey,expectedArticleTagValue);
    }

    @Test
    public void shouldGetContentsTagValueForXmlString() throws Exception {
        final String contentsTagPropertyKey = "reader.xml.contentsName";
        final String expectedArticleTagValue = "contents";

        getTagValueAndVerify(contentsTagPropertyKey,expectedArticleTagValue);
    }

    @Test
    public void shouldGetContentsTagValueForTxtString() throws Exception {
        final String contentsTagPropertyKey = "reader.txt.contentsName";
        final String expectedArticleTagValue = "contents";

        getTagValueAndVerify(contentsTagPropertyKey,expectedArticleTagValue);
    }
}