package ru.praktukum.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.Courier;
import ru.praktikum.yandex.CourierClient;
import static org.hamcrest.Matchers.equalTo;
import ru.praktikum.yandex.RandomCourier;

public class CreateCourierNegativeTest {

    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();

    Courier courier = new RandomCourier();


    @Test
    @DisplayName("No Login, the request returns 400 status code")
    @Description("No Login, the request returns 400 status code")
    public void checkCreateCourierWithoutLogin() {
        courier = new Courier("", randomPassword,randomName);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(400);

    }
    @Test
    @DisplayName("No Login, the request returns an error message")
    @Description("No Login, the request returns an error message")
    public void checkCreateCourierWithoutLoginResponseBody() {
        courier = new Courier("",randomPassword, randomName);
        courierClient.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("No Password, the request returns 400 status code")
    @Description("No Password, the request returns 400 status code")
    public void checkCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(400);

    }



    @Test
    @DisplayName("No Password, the request returns an error message")
    @Description("No Password, the request returns an error message")
    public void checkCreateCourierWithoutPasswordResponseBody() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }






    @Test
    @DisplayName("Creation of two couriers with equal login. Expect status code 409.")
    @Description("Creation of two couriers with equal login. Expect status code 409.")
    public void checkCreateCourierWithSameCredentials(){
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(409);
    }


    @Test
    @DisplayName("Creation of two couriers with equal login. Expect error message")
    @Description("Creation of two couriers with equal login. Expect error message")
    public void checkCreateCourierWithSameCredentialsResponseBody(){
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then().assertThat()
        .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteCourier(){
        courierClient.deleteCourier(courier);
    }
}


