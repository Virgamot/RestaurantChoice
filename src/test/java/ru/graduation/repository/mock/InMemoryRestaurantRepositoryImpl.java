package ru.graduation.repository.mock;

import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.util.RestaurantsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryRestaurantRepositoryImpl implements RestaurantRepository {

    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        RestaurantsUtil.RESTAURANTS.forEach(r -> {
            repository.put(r.getId(), r);
        });
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

    @Override
    public boolean delete(int id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return new ArrayList<>(repository.values());
    }
}
