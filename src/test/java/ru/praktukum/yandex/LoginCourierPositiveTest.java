package ru.praktukum.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.Courier;
import ru.praktikum.yandex.CourierClient;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class LoginCourierPositiveTest {

    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();
    Courier courier = new Courier(randomLogin, randomPassword, randomName);


    @Test
    @DisplayName("successful request returns id")
    @Description("successful request returns id")
    public void checkLoginCourierSuccessReturnId() {
        courierClient.createCourier(courier);
        courierClient.loginCourier(courier)
                .then().assertThat().body("id",notNullValue());


    }


    @After
    public void deleteCourier() {
        courierClient.deleteCourier(courier);
    }

}
