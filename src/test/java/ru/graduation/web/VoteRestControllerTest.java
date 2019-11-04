package ru.graduation.web;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.util.exception.ErrorType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.ADMIN;
import static ru.graduation.UserTestData.USER;
import static ru.graduation.UserTestData.USER_ID;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Test
    void testVoteFor() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        Restaurant restaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertEquals(1, restaurant.getRating());
        assertEquals((int) userService.getWithRestaurant(USER_ID).getRestaurant().getId(), RESTAURANT1_ID);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testRevote() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:30")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        mockMvc.perform(put(REST_URL + RESTAURANT2_ID)
                .param("time", "10:35")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        Restaurant restaurant1 = restaurantRepository.get(RESTAURANT1_ID);
        assertEquals(0, restaurant1.getRating());

        Restaurant restaurant2 = restaurantRepository.get(RESTAURANT2_ID);
        assertEquals(1, restaurant2.getRating());

        User user = userService.getWithRestaurant(USER_ID);

        assertEquals((int) user.getRestaurant().getId(), RESTAURANT2_ID);
    }

    @Test
    void testCancelChoice() throws Exception {

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:30")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());


        mockMvc.perform(patch(REST_URL + RESTAURANT1_ID)
                .param("time", "10:35")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        Restaurant restaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertEquals(0, restaurant.getRating());
        assertNull(userService.get(USER_ID).getRestaurant());
    }

    @Test
    void testRevoteWithExpiredTime() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:30")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        mockMvc.perform(put(REST_URL + RESTAURANT2_ID)
                .param("time", "11:05")
                .with(userHttpBasic(USER)))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.TIME_EXPIRED))
                .andDo(print());
    }

    @Test
    void testCancelChoiceWithWrongUser() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:30")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        mockMvc.perform(patch(REST_URL + RESTAURANT1_ID)
                .param("time", "10:35")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }


}