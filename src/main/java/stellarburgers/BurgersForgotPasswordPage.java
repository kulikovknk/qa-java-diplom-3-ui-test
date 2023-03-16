package stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgersForgotPasswordPage extends BurgersBasePage {

    // кнопка "Выйти"
    private final By buttonLogIn = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']//*[text() = 'Войти']");

    public BurgersForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {

        driver.get(BURGERS_FORGOT_PASSWORD_PAGE);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));

    }

    public void clickLogInButton() {
        driver.findElement(buttonLogIn).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));
    }

    public boolean isMainPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_MAIN_PAGE);
    }

}
