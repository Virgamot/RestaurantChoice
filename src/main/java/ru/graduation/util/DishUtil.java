package ru.graduation.util;

import ru.graduation.model.Dish;
import ru.graduation.to.DishTo;

import java.util.List;

public class DishUtil {

    public static String dishesToString(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        dishes.forEach(dish -> {
            sb.append(String.format("[ %s : with price %f $ ]\n", dish.getDescription(), dish.getPrice()));
        });
        return sb.toString();
    }

    public static Dish getFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(),dishTo.getDescription(),dishTo.getPrice());
    }


}
