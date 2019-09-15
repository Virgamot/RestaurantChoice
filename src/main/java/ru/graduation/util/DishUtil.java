package ru.graduation.util;

import ru.graduation.model.Dish;

import java.util.List;

public class DishUtil {

    public static String dishesToString(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        dishes.forEach(dish -> {
            sb.append(String.format("[ %s : with price %f $ ]\n", dish.getDescription(), dish.getPrice()));
        });
        return sb.toString();
    }
}
