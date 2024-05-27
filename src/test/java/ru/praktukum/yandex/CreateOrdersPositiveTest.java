package ru.praktukum.yandex;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.praktikum.yandex.OrderClient;
import ru.praktikum.yandex.Orders;
import ru.praktikum.yandex.RandomCourier;
import ru.praktikum.yandex.RandomOrders;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.*;

public class CreateOrdersPositiveTest {



OrderClient orderClient = new OrderClient();

 Orders orders = new RandomOrders(null);

@Test
public void createOrder(){
  orderClient.createOrder(orders)
            .then().assertThat().statusCode(201)
            .and()
            .body("track", notNullValue());

}

}
