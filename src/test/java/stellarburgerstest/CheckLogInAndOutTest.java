package stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import stellarburgers.*;

import java.util.HashMap;

import static generator.UserAccountGenerator.getExistingUserAccount;
import static generator.UserAccountGenerator.getNewUserAccount;

public class CheckLogInAndOutTest extends BaseUITest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    // вход по кнопке «Войти в аккаунт» на главной
    public void logInViaButtonLogInAccountSuccessfulTest() {

        HashMap<String, String> userAccount = registerNewCustomer();

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);

        objBurgersMainPage.openPage();
        objBurgersMainPage.clickLoginButton();

        logInCustomer(userAccount);

        Assert.assertTrue("Ошибка при входе пользователя по кнопке «Войти в аккаунт» на главной странице",
                objBurgersMainPage.isMainPageOpened());

    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    // вход через кнопку «Личный кабинет»
    public void logInViaButtonPersonalAccountSuccessfulTest() {

        HashMap<String, String> userAccount = registerNewCustomer();

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);

        objBurgersMainPage.openPage();
        objBurgersMainPage.clickPersonalAccountButtonToLogin();

        logInCustomer(userAccount);

        Assert.assertTrue("Ошибка при входе пользователя через кнопку «Личный кабинет»",
                objBurgersMainPage.isMainPageOpened());

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    // вход через кнопку в форме регистрации
    public void logInViaRegistrationPageSuccessfulTest() {

        HashMap<String, String> userAccount = registerNewCustomer();

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        objBurgersRegisterPage.clickLogInButton();

        logInCustomer(userAccount);

        Assert.assertTrue("Ошибка при входе пользователя через кнопку в форме регистрации",
                objBurgersRegisterPage.isMainPageOpened());

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    // вход через кнопку в форме восстановления пароля
    public void logInViaForgotPasswordPageSuccessfulTest() {

        HashMap<String, String> userAccount = registerNewCustomer();

        BurgersForgotPasswordPage objBurgersForgotPasswordPage = new BurgersForgotPasswordPage(driver);

        objBurgersForgotPasswordPage.openPage();
        objBurgersForgotPasswordPage.clickLogInButton();

        logInCustomer(userAccount);

        Assert.assertTrue("Ошибка при входе пользователя через кнопку в форме восстановления пароля",
                objBurgersForgotPasswordPage.isMainPageOpened());

    }

    @Test
    @DisplayName("Тест на выход по кнопке «Выйти» в личном кабинете")
    // тест на выход по кнопке «Выйти» в личном кабинете
    public void logOutCustomerTest() {

        logInCustomer(getExistingUserAccount());

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickLogoutButton();

        Assert.assertTrue("Выход по кнопке «Выйти» в личном кабинете не работает",
                objBurgersAccountProfilePage.isLoginPageOpened());

    }

    private HashMap<String, String> registerNewCustomer() {

        HashMap<String, String> userAccount = getNewUserAccount(8,6);

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        objBurgersRegisterPage.fillInCustomerData(userAccount);

        objBurgersRegisterPage.clickRegisterButton();

        return userAccount;
    }

    private void logInCustomer(HashMap<String, String> userAccount) {

        BurgersLoginPage objBurgersLoginPage = new BurgersLoginPage(driver);

        objBurgersLoginPage.openPage();
        objBurgersLoginPage.fillInCustomerData(userAccount);
        objBurgersLoginPage.clickLoginButton();

    }

}
