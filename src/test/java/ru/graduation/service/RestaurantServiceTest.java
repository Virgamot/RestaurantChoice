package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.DishTestData;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.UserTestData.ADMIN_ID;
import static ru.graduation.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @Test
    void testSave() throws Exception {
        Restaurant created = RestaurantTestData.getCreated();
        restaurantService.save(created, ADMIN_ID);
        assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    void testDelete() throws Exception {
        List<Restaurant> listWithDeleted = new ArrayList<>(RESTAURANTS);
        restaurantService.delete(RESTAURANT1_ID, ADMIN_ID);
        listWithDeleted.remove(RESTAURANT_1);
        assertMatch(restaurantService.getAll(), listWithDeleted);
    }

    @Test
    void testGet() throws Exception {
        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void testGetAll() throws Exception {
        List<Restaurant> restaurants = restaurantService.getAll();
        assertMatch(restaurants, RESTAURANTS);
    }

    @Test
    void testGetWithDishes() throws Exception {
        Restaurant restaurant = restaurantService.getWithDishes(RESTAURANT1_ID);
        DishTestData.assertMatch(restaurant.getDishes(),Arrays.asList(DISH_1,DISH_2,DISH_3,DISH_4,DISH_5,DISH_6));
    }

    @Test
    void testVotingFor() throws Exception {
        restaurantService.voteFor(RESTAURANT1_ID, USER_ID);
        User user = userService.getWithRestaurant(USER_ID);
        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        assertThat(restaurant.getRating() == 1);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void testRevote() throws Exception {
        restaurantService.voteFor(RESTAURANT1_ID, USER_ID);
        restaurantService.voteFor(RESTAURANT2_ID, USER_ID);

        Restaurant firstRestaurant = restaurantService.get(RESTAURANT1_ID);
        assertThat(firstRestaurant.getRating() == 0);

        Restaurant secondRestauran = restaurantService.get(RESTAURANT1_ID);
        assertThat(secondRestauran.getRating() == 1);

        User user = userService.getWithRestaurant(USER_ID);
        assertMatch(user.getRestaurant(), RESTAURANT_2);
    }


}