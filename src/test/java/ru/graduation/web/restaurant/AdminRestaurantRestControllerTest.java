package ru.graduation.web.restaurant;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.graduation.DishTestData;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Restaurant;
import ru.graduation.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.TestUtil.readFromJson;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.ADMIN;
import static ru.graduation.web.json.JsonUtil.writeValue;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestController.REST_URL + "/";

    @Test
    void testGet() throws Exception {
        ResultActions action=mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1));

        Restaurant returned=readFromJson(action,Restaurant.class);

        DishTestData.assertMatch(returned.getDishes(),DISH_1,DISH_2,DISH_3,DISH_4,DISH_5,DISH_6);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1, RESTAURANT_2));
    }

    @Test
    void testCreate() throws Exception {
        Restaurant created = RestaurantTestData.getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(created)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setAddress("Updated address");

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(restaurantService.getWithDishes(RESTAURANT1_ID), updated);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(restaurantService.getAll(), RESTAURANT_2);
    }

    //TODO
    @Test
    @Disabled
    void testCreateInvalid() throws Exception {
    }

    //TODO
    @Test
    @Disabled
    void testCreateDuplicate() throws Exception {
    }

    //TODO
    @Test
    @Disabled
    void testUpdateInvalid() throws Exception {
    }

    //TODO
    @Test
    @Disabled
    void testUpdateDuplicate() throws Exception {
    }
}