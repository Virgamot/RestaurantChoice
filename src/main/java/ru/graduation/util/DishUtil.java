package ru.graduation.util;

import ru.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;

import static ru.graduation.util.RestaurantsUtil.RESTAURANT_1;
import static ru.graduation.util.RestaurantsUtil.RESTAURANT_2;

public class DishUtil {

    public static String dishesToString(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        dishes.forEach(dish -> {
            sb.append(String.format("[ %s : with price %f $ ]\n", dish.getDescription(), dish.getPrice()));
        });
        return sb.toString();
    }
}
