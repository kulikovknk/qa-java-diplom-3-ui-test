package stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class BurgersRegisterPage extends BurgersBasePage {

    // поле ввода "Имя"
    private final By fieldName = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[1]//input[@name = 'name']");
    // поле ввода "Email"
    private final By fieldEmail = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[2]//input[@name = 'name']");
    // поле ввода "Пароль"
    private final By fieldPassword = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[3]//input[@name = 'Пароль']");
    // кнопка "Зарегистрироваться"
    private final By buttonRegister = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//button[text() = 'Зарегистрироваться']");
    // кнопка "Войти"
    private final By buttonLogIn = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']//*[text() = 'Войти']");
    // надпись "Некорректный пароль". Отображается при попытке регистрации с паролем, не соответствующим условиям.
    private final By messageIncorrectPassword = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[3]//*[text() = 'Некорректный пароль']");

    public BurgersRegisterPage(WebDriver driver) {
        super(driver);
    }

    // открыть страницу
    public void openPage() {

        driver.get(BURGERS_REGISTER_PAGE);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));

    }

    public void fillInCustomerData(HashMap<String, String> userAccount) {

        driver.findElement(fieldName).sendKeys(userAccount.get("name"));
        driver.findElement(fieldEmail).sendKeys(userAccount.get("email"));
        driver.findElement(fieldPassword).sendKeys(userAccount.get("password"));

    }

    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    public void clickLogInButton() {
        driver.findElement(buttonLogIn).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));
    }

    public boolean isIncorrectPasswordMessageVisible() {
        return driver.findElement(messageIncorrectPassword).isDisplayed();
    }

    public boolean isMainPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_MAIN_PAGE);
    }

    public boolean isLoginPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_LOGIN_PAGE);
    }

}
