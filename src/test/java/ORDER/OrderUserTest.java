package ORDER;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderUserTest {
    private String accessToken;
    private BaseUrl baseUrl;
    protected final UserRandom random = new UserRandom();
    private UserCreate userCreate;
    private UserAssertion userAssertion;
    private OrderAssertion orderAssertion = new OrderAssertion();


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
    @DisplayName("Проверка списка заказа зарегестрированным пользователем")
    @Description("Можно получить список")
    public void checkCreateOrderInLoginUser() {
        ValidatableResponse response = baseUrl.getOrdersListUser(accessToken);
        orderAssertion.getOrderLoginUser(response);
    }
    @Test
    @DisplayName("Проверка списка заказа незарегестрированным пользователем")
    @Description("невозможно получить список")
    public void checkCreateOrderListNoLogin() {
        ValidatableResponse response = baseUrl.getOrdersListUser("123");
        orderAssertion.getOrderNoLoginUser(response);
    }

}
