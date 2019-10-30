package ru.graduation.util;

import ru.graduation.model.Dish;
import ru.graduation.to.DishTo;

public class DishUtil {
    public static Dish getFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(),dishTo.getName(),dishTo.getPrice());
    }
}
