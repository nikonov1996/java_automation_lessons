package django_helpdesk_demo;

import core.SeleniumBaseTest;
import core.TestListener;
import django_helpdesk_demo.Pages.MainPage;
import django_helpdesk_demo.Pages.TicketPage;
import django_helpdesk_demo.helpers.TestValues;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import readProperties.ConfigProvider;

import static django_helpdesk_demo.helpers.StringModifier.getUniqueString;

@ExtendWith(TestListener.class)
public class TicketCreateByNoAuthorizedUserTest extends SeleniumBaseTest {

    @Test
    @Owner("Никонов Владислав")
    @Description("Проверяется, " +
            "что созданный тикет корректно " +
            "сохраняется с установленными значениями " +
            "почты и телом текста")
    public void checkTicket(){

        String title = getUniqueString(TestValues.TEST_TITLE);

        TicketPage ticketPage = new MainPage().createTicket(title,TestValues.TEST_BODY,TestValues.TEST_EMAIL)
                .openLoginPage()
                .auth(ConfigProvider.DEMO_LOGIN,ConfigProvider.DEMO_PASSWORD)
                .findTicket(title);

        Assertions.assertTrue(ticketPage.getTitle().contains(title));
        Assertions.assertEquals(ticketPage.getBody(),TestValues.TEST_BODY);
        Assertions.assertEquals(ticketPage.getEmail(),TestValues.TEST_EMAIL);

    }
}
