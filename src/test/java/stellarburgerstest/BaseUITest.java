package stellarburgerstest;

import config.WebDriverFactory;

import client.UserClient;
import dto.*;
import generator.DeleteUserRequestGenerator;
import generator.LoginUserRequestGenerator;
import generator.LogoutUserRequestGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BaseUITest {

    protected static WebDriver driver;

    private UserClient userClient;
    private UserRequest userRequest;
    private UserLoginRequest userLoginRequest;
    private UserLogoutRequest userLogoutRequest;
    private UserDeleteRequest userDeleteRequest;

    @BeforeClass
    public static void startUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
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

    public void clearUserData(UserRequest userRequest) {

        this.userRequest = userRequest;

        logInUser();
        logOutUser();
        deleteUser();

    }

    public void createUser(UserRequest userRequest) {

        this.userRequest = userRequest;

        userClient.createUser(userRequest)
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true))
                .and()
                .body("accessToken", notNullValue());

    }


    public void logInUser() {

        if (userRequest != null) {

            userLoginRequest = LoginUserRequestGenerator.from(userRequest);

            //  проверим, что успешно зашли под созданной учетной записью
            ValidatableResponse response = userClient.loginUser(userLoginRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("accessToken", notNullValue());

            // сохраним токены для дальнейшего выхода и удаления пользователя
            userLoginRequest.setRefreshToken(response.extract().path("refreshToken"));
            userLoginRequest.setAuthorization(response.extract().path("accessToken"));

        }

    }



    public void logOutUser() {

        if (userLoginRequest.getRefreshToken() != null) {

            userLogoutRequest = LogoutUserRequestGenerator.from(userLoginRequest);

            // разлогинимся
            userClient.logoutUser(userLogoutRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("message", equalTo("Successful logout"));

        }

    }

    public void deleteUser() {

        if (userLoginRequest.getAuthorization() != null) {

            userDeleteRequest = DeleteUserRequestGenerator.from(userLoginRequest);

            userClient.deleteUser(userDeleteRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_ACCEPTED)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("message", equalTo("User successfully removed"));

        }
    }

}
