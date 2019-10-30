package ru.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.graduation.model.Dish;
import ru.graduation.to.DishTo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.graduation.RestaurantTestData.RESTAURANT2_ID;
import static ru.graduation.RestaurantTestData.RESTAURANT_1;
import static ru.graduation.RestaurantTestData.RESTAURANT_2;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;
import static ru.graduation.web.json.JsonUtil.writeIgnoreProps;


public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 2;

    public static final Dish DISH_1 = new Dish(DISH1_ID, "Chicken soup", 2.5, RESTAURANT_1);
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, "Russian salad", 2d, RESTAURANT_1);
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, "Borscht", 3d, RESTAURANT_1);
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, "Beef chop", 3.2, RESTAURANT_1);
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, "Lamb chop", 3.4, RESTAURANT_1);
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, "Cacao", 1d, RESTAURANT_1);

    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, "Mushroom soup", 2.5, RESTAURANT_2);
    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, "Vinaigrette", 2d, RESTAURANT_2);
    public static final Dish DISH_9 = new Dish(DISH1_ID + 8, "Dumplings", 3d, RESTAURANT_2);
    public static final Dish DISH_10 = new Dish(DISH1_ID + 9, "Chicken chop", 2.5, RESTAURANT_2);
    public static final Dish DISH_11 = new Dish(DISH1_ID + 10, "Coffee", 1.2, RESTAURANT_2);

    public static final List<Dish> DISHES = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6,
            DISH_7, DISH_8, DISH_9, DISH_10, DISH_11);

    public static Dish getCreated() {
        return new Dish(null, "Created dish", 20d, RESTAURANT_2);
    }

    public static DishTo getCreatedTo() {
        return new DishTo(null, "Created dish", 20d, RESTAURANT2_ID);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... excpected) {
        assertMatch(actual, Arrays.asList(excpected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Dish expected) {
        return content().json(writeIgnoreProps(expected, "restaurant"));
    }

    public static ResultMatcher contentJson(List<Dish> expected) {
        return content().json(writeIgnoreProps(expected, "restaurant"));
    }
}
