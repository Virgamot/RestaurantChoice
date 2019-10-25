package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.util.exception.IllegalRequestDataException;
import ru.graduation.util.exception.TimeExpiredException;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.UserTestData.ADMIN_ID;
import static ru.graduation.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private static LocalTime mockTime = LocalTime.of(10, 0);

    @Test
    void testVoteFor() throws Exception {
        voteService.voteFor(RESTAURANT1_ID, USER_ID, mockTime);
        User user = userRepository.get(USER_ID);
        Restaurant restaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertEquals(1, restaurant.getRating());
        assertEquals(RESTAURANT1_ID, (int) user.getRestaurant().getId());
    }

    @Test
    void testCancelChoice() throws Exception {
        voteService.voteFor(RESTAURANT2_ID, USER_ID, mockTime);
        voteService.cancelChoice(RESTAURANT2_ID, USER_ID, mockTime);

        Restaurant restaurant = restaurantRepository.get(RESTAURANT2_ID);
        assertEquals(0, restaurant.getRating());

        User user = userRepository.getWithRestaurant(USER_ID);
        assertNull(user.getRestaurant());
    }

    @Test
    void testRevote() throws Exception {
        voteService.voteFor(RESTAURANT1_ID, USER_ID, mockTime);

        voteService.voteFor(RESTAURANT2_ID, USER_ID, mockTime);

        Restaurant firstRestaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertEquals(0, firstRestaurant.getRating());

        Restaurant secondRestaurant = restaurantRepository.get(RESTAURANT2_ID);
        assertEquals(1, secondRestaurant.getRating());

        User user = userRepository.getWithRestaurant(USER_ID);
        assertMatch(user.getRestaurant(), RESTAURANT_2);
    }

    @Test
    void testCancelChoiceWithWrongUser() throws Exception {
        voteService.voteFor(RESTAURANT2_ID,ADMIN_ID,mockTime);
        assertThrows(IllegalRequestDataException.class, () -> voteService.cancelChoice(RESTAURANT2_ID, USER_ID, mockTime));
    }

    @Test
    void testCancelChoiceWhenTimeHasPassed() throws Exception {
        voteService.voteFor(RESTAURANT2_ID,USER_ID,mockTime);
        assertThrows(TimeExpiredException.class, () -> voteService.cancelChoice(RESTAURANT2_ID, USER_ID, LocalTime.of(12, 0)));
    }

    @Test
    void testRevoteWWhenTimeHasPassed() throws Exception {
        voteService.voteFor(RESTAURANT1_ID, USER_ID, mockTime);
        assertThrows(TimeExpiredException.class, () -> voteService.voteFor(RESTAURANT2_ID, USER_ID, LocalTime.of(12, 0)));
    }

}