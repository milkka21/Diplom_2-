package USER;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.BaseUrl;
import org.example.UserAssertion;
import org.example.UserCreate;
import org.example.UserRandom;
import org.junit.Before;
import org.junit.Test;

public class UserCreateTest {
    private BaseUrl baseUrl;
    protected final UserRandom random = new UserRandom();
    private UserAssertion userAssertion;
    private UserCreate userCreate;

    private String accessToken;


    @Before
    @Step("Предусловия для создания Пользователя")
    public void setUp() {
        baseUrl = new BaseUrl();
        userCreate = random.random();   //uniquser
        userAssertion = new UserAssertion();
    }

    @Test
    @DisplayName("Создание нового Пользователя")
    @Description("Пользователя можно создать")
    public void userCanBeCreated() {
        ValidatableResponse create = baseUrl.register(userCreate);
        accessToken = userAssertion.assertCreationSusses(create);
    }
    @Test
    @DisplayName("Создание Пользователя без заполнения поля логин")
    @Description("Пользователя нельзя создать без логина. Проверяем статус код и сообщение об ошибке")
    public void userCantCreatedNoEmail() {
        userCreate.setEmail(null);
        ValidatableResponse create = baseUrl.register(userCreate);
        userAssertion.assertCreationUserNoRequiredField(create);
    }

    @Test
    @DisplayName("Создание Пользователя без заполнения поля пароль")
    @Description("Пользователя нельзя создать без пароля. Проверяем статус код и сообщение об ошибке")
    public void userCantCreatedNoPassword() {
        userCreate.setPassword(null);
        ValidatableResponse create = baseUrl.register(userCreate);
        userAssertion.assertCreationUserNoRequiredField(create);
    }

    @Test
    @DisplayName("Создание Пользователя без заполнения поля имени")
    @Description("Пользователя нельзя создать без имени. Проверяем статус код и сообщение об ошибке")
    public void userCantCreatedNoName() {
        userCreate.setName(null);
        ValidatableResponse create = baseUrl.register(userCreate);
        userAssertion.assertCreationUserNoRequiredField(create);
    }
    @Test
    @DisplayName("Создание Пользователя  который уже зарегистрирован")
    @Description("Пользователя нельзя создать")
    public void userCantCreated() {
        ValidatableResponse create = baseUrl.register(userCreate);
        accessToken = userAssertion.assertCreationSusses(create);

        ValidatableResponse create2 = baseUrl.register(userCreate);
        userAssertion.assertCreationUserFailed(create2);
    }


}
