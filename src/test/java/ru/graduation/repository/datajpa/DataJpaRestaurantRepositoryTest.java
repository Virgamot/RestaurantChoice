package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.graduation.RestaurantTestData.*;

class DataJpaRestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RestaurantRepository repository;

    @Test
    void save() {
        Restaurant created = getCreated();
        repository.save(created);
        assertMatch(repository.getAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    void delete() {
        List<Restaurant> listWithDeleted = new ArrayList<>(RESTAURANTS);
        repository.delete(RESTAURANT1_ID);
        listWithDeleted.remove(RESTAURANT_1);
        assertMatch(repository.getAll(), listWithDeleted);
    }

    @Test
    void get() {
        Restaurant restaurant = repository.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void getAll() {
        List<Restaurant> restaurants = repository.getAll();
        assertMatch(restaurants, RESTAURANTS);
    }
}