package ru.graduation.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.DishTestData;
import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;
import ru.graduation.to.DishTo;
import ru.graduation.web.AbstractControllerTest;
import ru.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.RESTAURANT1_ID;
import static ru.graduation.RestaurantTestData.RESTAURANT2_ID;
import static ru.graduation.TestUtil.*;
import static ru.graduation.UserTestData.ADMIN;
import static ru.graduation.UserTestData.USER;


class AdminDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishRestController.REST_URL + "/";

    @Test
    void testCreate() throws Exception {

        DishTo createdTo = DishTestData.getCreatedTo();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andExpect(status().isOk());

        Dish returned = readFromJson(action, Dish.class);
        assertMatch(dishRepository.getAll(), DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9, DISH_10, DISH_11, returned);
        assertMatch(restaurantService.getWithDishes(RESTAURANT2_ID).getDishes(), DISH_7, DISH_8, DISH_9, DISH_10, DISH_11, returned);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        assertMatch(dishRepository.getAll(), DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9, DISH_10, DISH_11);
        assertMatch(restaurantService.getWithDishes(RESTAURANT1_ID).getDishes(), DISH_2, DISH_3, DISH_4, DISH_5, DISH_6);
    }

    @Test
    void testDeleteUnAuth() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}