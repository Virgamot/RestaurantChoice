package ru.graduation.util;

import ru.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;

import static ru.graduation.util.RestaurantsUtil.RESTAURANT_1;
import static ru.graduation.util.RestaurantsUtil.RESTAURANT_2;

public class DishUtil {
    public static final Dish DISH_1 = new Dish(RESTAURANT_1, "Chicken soup", 2.5);
    public static final Dish DISH_2 = new Dish(RESTAURANT_1, "Russian salad", 2d);
    public static final Dish DISH_3 = new Dish(RESTAURANT_1, "Borscht", 3d);
    public static final Dish DISH_4 = new Dish(RESTAURANT_1, "Beef chop", 3.2);
    public static final Dish DISH_5 = new Dish(RESTAURANT_1, "Lamb chop", 3.4);
    public static final Dish DISH_6 = new Dish(RESTAURANT_1, "Cacao", 1d);

    public static final Dish DISH_7 = new Dish(RESTAURANT_2, "Mushroom soup", 2.5);
    public static final Dish DISH_8 = new Dish(RESTAURANT_2, "Vinaigrette", 2d);
    public static final Dish DISH_9 = new Dish(RESTAURANT_2, "Dumplings", 3d);
    public static final Dish DISH_10 = new Dish(RESTAURANT_2, "Chicken chop", 2.5);
    public static final Dish DISH_11 = new Dish(RESTAURANT_2, "Coffee", 1.2);

    public static final List<Dish> DISHES = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6,
            DISH_7, DISH_8, DISH_9, DISH_10, DISH_11);

    public static String dishesToString(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        dishes.forEach(dish -> {
            sb.append(String.format("[ %s : with price %f $ ]\n", dish.getDescription(), dish.getPrice()));
        });
        return sb.toString();
    }
}
