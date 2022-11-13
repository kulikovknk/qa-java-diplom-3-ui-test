package stellarburgers;

import dto.UserRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public void fillInCustomerData(UserRequest userRequest) {

        driver.findElement(fieldName).sendKeys(userRequest.getName());
        driver.findElement(fieldEmail).sendKeys(userRequest.getEmail());
        driver.findElement(fieldPassword).sendKeys(userRequest.getPassword());

    }

    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();

        // указываю явное ожидание, чтобы дождаться загрузки любой страницы,
        // которая должна загрузиться в зависимости от результата регистрации (положительного или нет)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    public void clickLogInButton() {
        driver.findElement(buttonLogIn).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));
    }

    public boolean isIncorrectPasswordMessageVisible() {

        List<WebElement> elements = driver.findElements(messageIncorrectPassword);

        return elements.size() != 0;

    }

    public boolean isMainPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_MAIN_PAGE);
    }

    public boolean isLoginPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_LOGIN_PAGE);
    }

}
