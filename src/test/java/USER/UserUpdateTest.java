package USER;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.BaseUrl;
import org.example.UserAssertion;
import org.example.UserCreate;
import org.example.UserRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserUpdateTest {

    private String accessToken;
    private BaseUrl baseUrl;
    protected final UserRandom random = new UserRandom();
    private UserCreate userCreate;

    private UserAssertion userAssertion;

    @Before
    @Step("Предусловия для изменения данных")
    public void setUp() {
        baseUrl = new BaseUrl();
        userCreate = random.random();   //uniquser
        userAssertion = new UserAssertion();
        ValidatableResponse create = baseUrl.register(userCreate);
        accessToken = userAssertion.assertCreationSusses(create);
    }
    @Test
    @DisplayName("Изменение данных авторизированного пользователя")
    @Description("изменение email зарегестрированным пользователем")
    public void checkUpdateUserEmail() {
        userCreate.setEmail(userCreate.getEmail() + "qwe");
        ValidatableResponse response = baseUrl.update(accessToken, userCreate);
        userAssertion.updateUserSusses(response);
    }
    @Test
    @DisplayName("Изменение данных авторизированного пользователя")
    @Description("изменение пароля зарегестрированным пользователем")
    public void checkUpdateUserPassword() {
        userCreate.setPassword(userCreate.getPassword() + "qwe");
        ValidatableResponse response = baseUrl.update(accessToken, userCreate);
        userAssertion.updateUserSusses(response);
    }
    @Test
    @DisplayName("Изменение данных авторизированного пользователя")
    @Description("изменение имени зарегестрированным пользователем")
    public void checkUpdateUserName() {
        userCreate.setName(userCreate.getName() + "qwe");
        ValidatableResponse response = baseUrl.update(accessToken, userCreate);
        userAssertion.updateUserSusses(response);
    }


    @Test
    @DisplayName("Изменение данных неавторизированного пользователя")
    @Description("Проверка возможности изменения имени незарегестрированным пользователем")
    public void checkUpdateUserNameNoLogin() {
        userCreate.setName(userCreate.getName() + "qwe");
        ValidatableResponse response = baseUrl.update("null", userCreate);
        userAssertion.updateUserFailed(response);
    }
    @Test
    @DisplayName("Изменение данных неавторизированного пользователя")
    @Description("Проверка возможности изменения пароля незарегестрированным пользователем")
    public void checkUpdateUserPasswordNoLogin() {
        userCreate.setPassword(userCreate.getPassword() + "qwe");
        ValidatableResponse response = baseUrl.update("null", userCreate);
        userAssertion.updateUserFailed(response);
    }
    @Test
    @DisplayName("Изменение данных неавторизированного пользователя")
    @Description("Проверка возможности изменения email незарегестрированным пользователем")
    public void checkUpdateUserEmailNoLogin() {
        userCreate.setEmail(userCreate.getEmail() + "qwe");
        ValidatableResponse response = baseUrl.update("null", userCreate);
        userAssertion.updateUserFailed(response);
    }


}
