package USER;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.Before;
import org.junit.Test;


public class UserLoginTest {

    private String accessToken;
    private BaseUrl baseUrl;
    protected final UserRandom random = new UserRandom();
    private UserCreate userCreate;

    private UserAssertion userAssertion;

    @Before
    @Step("Предусловия для логина")
        public void setUp() {
        baseUrl = new BaseUrl();
        userCreate = random.random();   //uniquser
        userAssertion = new UserAssertion();
        ValidatableResponse create = baseUrl.register(userCreate);
        accessToken = userAssertion.assertCreationSusses(create);
    }
    @Test
    @DisplayName("Логин Пользователя существующими данными")
    @Description("Можно войти с существующими данными")
    public void UserCanSuccessfullyLogin() {
        UserLogin userLogin = UserLogin.from(userCreate);
        ValidatableResponse login = baseUrl.login(userLogin);
        userAssertion.assertLoginSuccess(login);
    }
    @Test
    @DisplayName("Логин Пользователя с неправильным паролем")
    @Description("Попытка входа с неправильным паролем. Проверка сообщения об ошибке")
    public void userLoginUnsuccessfullyWithoutPassword() {
        UserLogin userLogin = UserLogin.from(userCreate);
        UserCanSuccessfullyLogin();
        userLogin.setPassword(userLogin.getPassword()+ "uy");
        ValidatableResponse login = baseUrl.login(userLogin);
        userAssertion.assertLoginFailed(login);
    }


    @Test
    @DisplayName("Логин Пользователя с неправильным полем Логин")
    @Description("Попытка входа с неправильным полем Логин. Проверка сообщения об ошибке")
    public void userLoginUnsuccessfullyWithoutEmail() {
        UserLogin userLogin = UserLogin.from(userCreate);
        UserCanSuccessfullyLogin();
        userLogin.setEmail(userLogin.getEmail()+ "uywe");
        ValidatableResponse login = baseUrl.login(userLogin);
        userAssertion.assertLoginFailed(login);
    }

}
