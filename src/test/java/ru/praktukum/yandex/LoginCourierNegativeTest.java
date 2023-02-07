package ru.praktukum.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.Courier;
import ru.praktikum.yandex.CourierClient;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierNegativeTest {


    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();
    Courier courier = new Courier(randomLogin, randomPassword, randomName);


    @Test
    @DisplayName("для авторизации нужно передать все обязательные поля;")
    @Description("если Password нет, запрос возвращает ошибку;")
    public void checkLoginWithoutPasswordReturnFalse() {
        courier = new Courier(randomLogin, "", "");
        courierClient.createCourier(courier);
        courierClient.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("для авторизации нужно передать все обязательные поля;")
    @Description("если Login нет, запрос возвращает ошибку;")
    public void checkLoginWithoutLoginReturnFalse() {
        courier = new Courier("", randomPassword, "");
        courierClient.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }


    @Test
    @DisplayName("система вернёт ошибку, если неправильно указать логин или пароль;")
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
    public void checkLoginNotExistReturnFalse() {
        courier = new Courier("ooo", "", "");

        courierClient.loginCourier(courier)
                .then().assertThat().statusCode(400)
                .and().body("message", equalTo("Недостаточно данных для входа"));

    }

    @After
    public void deleteCourier() {
        courierClient.deleteCourier(courier);
    }
}
