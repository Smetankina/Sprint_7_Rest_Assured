package ru.praktukum.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.yandex.OrderClient;
import java.util.ArrayList;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrderTest {

    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("The body of response return list of orders")
    @Description("The body of response return list of orders")
    public void getOrderReturnList() {

        orderClient.getOrder()
                .then()
                .body("orders", notNullValue());
    }
    @Test
    @DisplayName("The body of response return list of orders 2 var")
    @Description("The body of response return list of orders 2 var")
    public void getOrderReturnList1() {
        //прокомментируйте, пожалуйста, можно ли сделать так?
        orderClient.getOrder()
                .then()
                .extract().path("orders")
                .getClass().equals(ArrayList.class);

    }
    }

