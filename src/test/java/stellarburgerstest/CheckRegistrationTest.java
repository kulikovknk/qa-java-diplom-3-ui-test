package stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import stellarburgers.BurgersRegisterPage;

import static generator.UserAccountGenerator.getNewUserAccount;

public class CheckRegistrationTest extends BaseUITest {

    @Test
    @DisplayName("Тест на успешную регистрацию нового покупателя")
    // тест на успешную регистрацию нового покупателя
    public void registrationIsSuccessfulTest() {

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        // минимальный пароль - 6 символов. Ошибки ввода пароля быть не должно.
        objBurgersRegisterPage.fillInCustomerData(getNewUserAccount(8, 6));
        objBurgersRegisterPage.clickRegisterButton();

        Assert.assertFalse("Переход на страницу входа не выполнен. Ошибка регистрации пользователя.",
                objBurgersRegisterPage.isLoginPageOpened());

    }

    @Test
    @DisplayName("Тест на ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    //  тест на ошибку для некорректного пароля. Минимальный пароль — шесть символов.
    public void registrationWithIncorrectPasswordTest() {

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        // минимальный пароль - 6 символов. Если пароль - 5 символов, должна отобразиться ошибка "Некорректный пароль".
        objBurgersRegisterPage.fillInCustomerData(getNewUserAccount(8, 5));
        objBurgersRegisterPage.clickRegisterButton();

        Assert.assertTrue("Пользователь с некорректным паролем зарегистрирован. Ошибка \"Некорректный пароль\" не отобразилась",
                objBurgersRegisterPage.isIncorrectPasswordMessageVisible() && !objBurgersRegisterPage.isLoginPageOpened());

    }

}
