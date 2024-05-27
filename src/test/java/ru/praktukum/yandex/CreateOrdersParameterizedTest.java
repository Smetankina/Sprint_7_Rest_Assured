
package ru.praktukum.yandex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.yandex.OrderClient;
import ru.praktikum.yandex.Orders;
import ru.praktikum.yandex.RandomOrders;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrdersParameterizedTest {

    private final Orders orders;

    OrderClient orderClient = new OrderClient();

    public CreateOrdersParameterizedTest(Orders orders) {
        this.orders = orders;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                //можно указать один из цветов — BLACK или GREY;
                {new RandomOrders(new String[]{"BLACK"})},
                //можно указать один из цветов — BLACK или GREY;
                {new RandomOrders(new String[]{"GREY"})},
                //можно указать оба цвета;
                {new RandomOrders(new String[]{"BLACK", "GREY"})},
                //можно совсем не указывать цвет;
                {new RandomOrders(null)},
        };
    }


    @Test
    public void createOrder() {
        orderClient.createOrder(orders)
                .then().assertThat().statusCode(201)
                .and()
                //тело ответа содержит track.
                .body("track", notNullValue());

    }

}

