package ru.graduation.repository.mock;

import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.graduation.util.RestaurantsUtil.RESTAURANT_1;
import static ru.graduation.util.RestaurantsUtil.RESTAURANT_2;

@Repository
public class InMemoryRestaurantRepositoryImpl implements RestaurantRepository {

    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        repository.put(RESTAURANT_1.getId(), RESTAURANT_1);
        repository.put(RESTAURANT_2.getId(), RESTAURANT_2);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {

        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            repository.put(restaurant.getId(), restaurant);
            return restaurant;
        } else {
            repository.put(restaurant.getId(), restaurant);
        }
        return null;
    }


    //TODO
    @Override
    public boolean delete(int id) {
        return false;
    }

    //TODO
    @Override
    public Restaurant get(int id) {
        return null;
    }

    //TODO
    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
