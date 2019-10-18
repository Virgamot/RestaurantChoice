package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.RestaurantTestData.*;

class DataJpaRestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RestaurantRepository repository;

    @Test
    void testVoteFor() throws Exception {
        repository.increaseRating(RESTAURANT1_ID);
        assertThat(repository.getWithDishes(RESTAURANT1_ID).getRating() == 1);
    }

    @Test
    void testVoteAgainst() throws Exception {
        Restaurant created = getCreated();
        created.setRating(11);
        Restaurant returned = repository.save(created);
        int returnedId = returned.getId();
        repository.decreaseRating(returnedId);
        assertThat(repository.getWithDishes(returnedId).getRating() == 10);
    }

    @Test
    void testGetReference() throws Exception {
        Restaurant restaurantRef = repository.getReference(RESTAURANT2_ID);
        assertThat(restaurantRef.getId()==RESTAURANT2_ID);
    }


}