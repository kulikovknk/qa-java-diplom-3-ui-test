package stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import stellarburgers.BurgersAccountProfilePage;
import stellarburgers.BurgersLoginPage;
import stellarburgers.BurgersMainPage;

import java.util.HashMap;

import static generator.UserAccountGenerator.getExistingUserAccount;

public class CheckGoToPageTest extends BaseUITest {

    @Test
    @DisplayName("Тест на переход по клику в «Личный кабинет»")
    // тест на переход по клику в «Личный кабинет»
    public void goToLogInPageTest() {

        logInCustomer(getExistingUserAccount());

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        Assert.assertTrue("Переход в личный кабинет по кнопке \"Личный кабинет\" не работает",
                objBurgersMainPage.isAccountProfilePageOpened());
    }

    @Test
    @DisplayName("Тест на переход из личного кабинета в конструктор по клику на «Конструктор»")
    // тест на переход из личного кабинета в конструктор по клику на «Конструктор»
    public void clickConstructorButtonTest() {

        logInCustomer(getExistingUserAccount());

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickConstructorButton();

        Assert.assertTrue("Переход на главную страницу по кнопке \"Конструктор\" не работает",
                objBurgersAccountProfilePage.isMainPageOpened());

    }

    @Test
    @DisplayName("Тест на переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    // Тест на переход из личного кабинета в конструктор по клику на логотип Stellar Burgers
    public void clickStellarBurgerLogoTest() {

        logInCustomer(getExistingUserAccount());

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickStellarBurgersLogo();

        Assert.assertTrue("Переход на главную страницу при нажатии на логотип \"Stellar Burger\" не работает",
                objBurgersAccountProfilePage.isMainPageOpened());

    }

    private void logInCustomer(HashMap<String, String> userAccount) {

        BurgersLoginPage objBurgersLoginPage = new BurgersLoginPage(driver);

        objBurgersLoginPage.openPage();
        objBurgersLoginPage.fillInCustomerData(userAccount);
        objBurgersLoginPage.clickLoginButton();

    }

}
