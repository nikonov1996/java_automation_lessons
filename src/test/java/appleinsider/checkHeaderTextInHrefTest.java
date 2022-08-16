package appleinsider;

import appleinsider.Pages.MainPage;
import core.SelenideBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class checkHeaderTextInHrefTest extends SelenideBaseTest {
    private final static String BASE_URL = "https://appleinsider.ru/";
    private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
    private final static String EXPECTED_STRING = "iphone-11";

    @Test
    public void checkArticleHref(){
        Assertions.assertTrue(new MainPage(BASE_URL)
                .searchByText(SEARCH_STRING)
                .getHrefFromFirstArticle().contains(EXPECTED_STRING));
    }
}
