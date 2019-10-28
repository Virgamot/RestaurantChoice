package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.DishTestData;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.graduation.DishTestData.*;
import static ru.graduation.DishTestData.DISH_5;
import static ru.graduation.DishTestData.DISH_6;
import static ru.graduation.RestaurantTestData.*;

class DataJpaRestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RestaurantRepository repository;

    @Test
    void testVoteFor() throws Exception {
        repository.increaseRating(RESTAURANT1_ID);
        assertEquals(1, repository.getWithDishes(RESTAURANT1_ID).getRating());
    }

    @Test
    void testVoteAgainst() throws Exception {
        Restaurant created = RestaurantTestData.getCreated();
        created.setRating(11);
        Restaurant returned = repository.save(created);
        int returnedId = returned.getId();
        repository.decreaseRating(returnedId);
        assertEquals(10, repository.getWithDishes(returnedId).getRating());
    }

    @Test
    void testGetReference() throws Exception {
        Restaurant restaurantRef = repository.getReference(RESTAURANT2_ID);
        assertEquals((int) restaurantRef.getId(), RESTAURANT2_ID);
    }


    @Test
    void testSave() throws Exception {
        Restaurant created = RestaurantTestData.getCreated();
        repository.save(created);
        assertMatch(repository.getAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    void testDelete() throws Exception {
        List<Restaurant> listWithDeleted = new ArrayList<>(RESTAURANTS);
        repository.delete(RESTAURANT1_ID);
        listWithDeleted.remove(RESTAURANT_1);
        assertMatch(repository.getAll(), listWithDeleted);
    }

    @Test
    void testGet() throws Exception{
        Restaurant restaurant= repository.get(RESTAURANT1_ID);
        assertMatch(restaurant,RESTAURANT_1);
    }

    @Test
    void testGetAll() throws Exception {
        List<Restaurant> restaurants = repository.getAll();
        assertMatch(restaurants, RESTAURANTS);
    }

    @Test
    void testGetWithDishes() throws Exception {
        Restaurant restaurant = repository.getWithDishes(RESTAURANT1_ID);
        DishTestData.assertMatch(restaurant.getDishes(), Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6));
    }
}