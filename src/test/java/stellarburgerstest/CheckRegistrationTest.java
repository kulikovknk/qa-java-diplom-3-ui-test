package stellarburgerstest;

import dto.UserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import stellarburgers.BurgersRegisterPage;

import static generator.UserRequestGenerator.getNewUserRequest;

public class CheckRegistrationTest extends BaseUITest {

    @Test
    @DisplayName("Тест на успешную регистрацию нового покупателя")
    // тест на успешную регистрацию нового покупателя
    public void registrationIsSuccessfulTest() {

        UserRequest userRequest = getNewUserRequest(8, 6);

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        // минимальный пароль - 6 символов. Ошибки ввода пароля быть не должно.
        objBurgersRegisterPage.fillInCustomerData(userRequest);
        objBurgersRegisterPage.clickRegisterButton();

        boolean isUserRegistered = (!objBurgersRegisterPage.isIncorrectPasswordMessageVisible()
                && objBurgersRegisterPage.isLoginPageOpened());

        if (isUserRegistered) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Переход на страницу входа не выполнен. " +
                        "Ошибка регистрации пользователя.", isUserRegistered);

    }

    @Test
    @DisplayName("Тест на ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    //  тест на ошибку для некорректного пароля. Минимальный пароль — шесть символов.
    public void registrationWithIncorrectPasswordTest() {

        // минимальный пароль - 6 символов. Если пароль - 5 символов, должна отобразиться ошибка "Некорректный пароль".
        UserRequest userRequest = getNewUserRequest(8, 5);

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        // минимальный пароль - 6 символов. Если пароль - 5 символов, должна отобразиться ошибка "Некорректный пароль".
        objBurgersRegisterPage.fillInCustomerData(userRequest);
        objBurgersRegisterPage.clickRegisterButton();

        boolean isUserNotRegistered = (objBurgersRegisterPage.isIncorrectPasswordMessageVisible()
                && !objBurgersRegisterPage.isLoginPageOpened());


        if (!isUserNotRegistered) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Пользователь с некорректным паролем зарегистрирован. " +
                        "Ошибка \"Некорректный пароль\" не отобразилась", isUserNotRegistered);

    }

}
