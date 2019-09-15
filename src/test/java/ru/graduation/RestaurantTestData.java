package ru.graduation;

import ru.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static ru.graduation.model.BaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "St.Petersburg, Leninskiy Prospect st., 21");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT2_ID, "St.Petersburg, Panfilova st., 87");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2);
}
