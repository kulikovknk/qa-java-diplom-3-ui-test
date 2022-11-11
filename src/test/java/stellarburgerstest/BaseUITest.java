package stellarburgerstest;

import config.WebDriverFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

public class BaseUITest {

    protected static WebDriver driver;

    @BeforeClass
    public static void startUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @After
    public void clearData() {

        driver.manage().deleteAllCookies();
        ((WebStorage) driver).getSessionStorage().clear();
        ((WebStorage) driver).getLocalStorage().clear();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
