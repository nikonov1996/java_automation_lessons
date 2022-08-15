package step;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import net.jodah.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class KlavagonkiStep {

    private final SelenideElement closeButton = $x("//input[@value='Закрыть']");
    private final SelenideElement startGameButton = $x("//a[@id='host_start']");
    private final SelenideElement highlightWord = $x("//span[@id='typefocus']");
    private final SelenideElement inputField = $x("//input[@id='inputtext']");
    private final SelenideElement afterFocusWord = $x("//span[@id='afterfocus']");
    private final SelenideElement resultValue = $("div#players  div.rating .stats div:nth-child(2) span>span");
    private String getCurrentWord(){
        return this.highlightWord.getText().replaceAll("c", "с").replaceAll("o","о");
    }
    @When("Start game")
    public void startGame() {
        closeButton.click();
        if(startGameButton.isDisplayed()) startGameButton.click();;
    }

    @And("Wait game starting")
    public void waitGameStarting() {
        highlightWord.click();
    }

    @And("Enter markered word in field by cycle")
    public void playGame() {
        while (true){
            String afterFocusSymbol = afterFocusWord.getText();
            inputField.sendKeys(getCurrentWord());
            if (afterFocusSymbol.equals(".")){
                inputField.sendKeys(".");
                break;
            }
            inputField.sendKeys(Keys.SPACE);
        }

    }

    @Then("See that game finished and entered symbols value more than {int} in one minute")
    public void endGame(int expectedGameResultValue) {
        String gameResult = resultValue.getText();
        int actualGameResultValue = Integer.parseInt(gameResult);
        Assertions.assertTrue(actualGameResultValue > expectedGameResultValue,"Bot game result (" + actualGameResultValue + ") mast be more than expected game result value (" + expectedGameResultValue + ")");
    }
}
