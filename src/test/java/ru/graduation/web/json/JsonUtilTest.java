package ru.graduation.web.json;

import org.junit.jupiter.api.Test;
import ru.graduation.model.Dish;

import java.util.List;

import static ru.graduation.DishTestData.DISHES;
import static ru.graduation.DishTestData.DISH_1;
import static ru.graduation.DishTestData.assertMatch;

class JsonUtilTest {

    @Test
    void testReadWriteValue() {
        String json = JsonUtil.writeValue(DISH_1);
        System.out.println(json);
        Dish dish1 = JsonUtil.readValue(json, Dish.class);
        assertMatch(DISH_1, dish1);
    }

    @Test
    void testReadWriteValues() {
        String json = JsonUtil.writeValue(DISHES);
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        assertMatch(DISHES, dishes);
    }
}