package ru.graduation.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.graduation.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.DishTestData.DISH1_ID;
import static ru.graduation.DishTestData.DISHES;
import static ru.graduation.DishTestData.DISH_1;
import static ru.graduation.TestUtil.contentJson;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.USER;


class UserDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserDishRestController.REST_URL + "/";

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH_1));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISHES));
    }
}