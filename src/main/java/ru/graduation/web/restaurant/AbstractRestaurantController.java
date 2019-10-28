package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

public class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAll() {
        LOG.info("getAll");
        return repository.getAll();
    }

    public Restaurant get(int id) {
        LOG.info("get restaurant with id={}", id);
        return repository.getWithDishes(id);
    }

    public Restaurant save(Restaurant restaurant, int userId) {
        LOG.info("create restaurant: {} by user, with id={}", restaurant, userId);
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant, int userId) {
        LOG.info("update restaurant: {} by user, with id={}", restaurant, userId);
        return repository.save(restaurant);
    }

    public void delete(int restaurantId, int userId) {
        LOG.info("delete restaurant with id={} by user, with id={}", restaurantId, userId);
        repository.delete(restaurantId);
    }
}
