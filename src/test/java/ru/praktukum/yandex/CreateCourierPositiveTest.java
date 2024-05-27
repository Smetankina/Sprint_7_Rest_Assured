package ru.praktukum.yandex;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.Courier;
import ru.praktikum.yandex.CourierClient;
import ru.praktikum.yandex.RandomCourier;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierPositiveTest {



    CourierClient courierClient = new CourierClient();
    Courier courier = new RandomCourier();

    @Test
    @DisplayName("request returns correct status code")
    @Description("request returns correct status code")
    public void checkCreateCourierStatusCode() {
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(201);

    }

    @Test
    @DisplayName("successful request returns ok: true;")
    @Description("successful request returns ok: true;")
    public void checkCreateCourierSuccessTrue() {
        courierClient.createCourier(courier)
                .then().assertThat().body("ok", equalTo(true));
    }
    @After
    public void deleteCourier(){
        courierClient.deleteCourier(courier);
    }
}