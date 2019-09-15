package ru.graduation;

import ru.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;

import static ru.graduation.RestaurantTestData.RESTAURANT_1;
import static ru.graduation.RestaurantTestData.RESTAURANT_2;
import static ru.graduation.model.BaseEntity.START_SEQ;


public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 2;

    public static final Dish DISH_1 = new Dish(DISH1_ID, RESTAURANT_1, "Chicken soup", 2.5);
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, RESTAURANT_1, "Russian salad", 2d);
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, RESTAURANT_1, "Borscht", 3d);
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, RESTAURANT_1, "Beef chop", 3.2);
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, RESTAURANT_1, "Lamb chop", 3.4);
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, RESTAURANT_1, "Cacao", 1d);

    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, RESTAURANT_2, "Mushroom soup", 2.5);
    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, RESTAURANT_2, "Vinaigrette", 2d);
    public static final Dish DISH_9 = new Dish(DISH1_ID + 8, RESTAURANT_2, "Dumplings", 3d);
    public static final Dish DISH_10 = new Dish(DISH1_ID + 9, RESTAURANT_2, "Chicken chop", 2.5);
    public static final Dish DISH_11 = new Dish(DISH1_ID + 10, RESTAURANT_2, "Coffee", 1.2);

    public static final List<Dish> DISHES = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6,
            DISH_7, DISH_8, DISH_9, DISH_10, DISH_11);
}
