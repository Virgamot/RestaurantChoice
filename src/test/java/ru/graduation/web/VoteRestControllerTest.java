package ru.graduation.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.USER;
import static ru.graduation.UserTestData.USER_ID;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void testVoteFor() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .param("time", "10:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());


        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        assertEquals(1, restaurant.getRating());
        assertMatch(userService.get(USER_ID).getRestaurant(), restaurant);
    }

    @Test
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

        assertMatch(userService.get(USER_ID).getRestaurant(), restaurant2);
    }


    @Test
    void testCancelChoice() throws Exception {

        //https://stackoverflow.com/questions/58509408/why-findbyid-returns-proxy-after-calling-getone-on-same-entity
        Restaurant fakeRestaurant = restaurantRepository.get(RESTAURANT1_ID);


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
}