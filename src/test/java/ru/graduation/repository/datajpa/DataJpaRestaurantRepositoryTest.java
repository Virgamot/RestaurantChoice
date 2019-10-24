package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Restaurant created = getCreated();
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


}