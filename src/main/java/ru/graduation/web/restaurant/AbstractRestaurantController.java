package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.util.List;

public class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    private RestaurantService service;

    public List<Restaurant> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public Restaurant get(int id) {
        LOG.info("get restaurant with id={}", id);
        return service.get(id);
    }

    public Restaurant save(Restaurant restaurant, int userId) {
        LOG.info("create restaurant: {} by user, with id={}", restaurant, userId);
        return service.save(restaurant, userId);
    }

    public Restaurant update(Restaurant restaurant, int userId) {
        LOG.info("update restaurant: {} by user, with id={}", restaurant, userId);
        return service.update(restaurant, userId);
    }

    public void delete(int restaurantId, int userId) {
        LOG.info("delete restaurant with id={} by user, with id={}", restaurantId, userId);
        service.delete(restaurantId, userId);
    }

    public Restaurant getWithDishes(int restaurantId, int userId) {
        LOG.info("get with dishes, restaurant id={}", restaurantId);
        return service.getWithDishes(restaurantId);
    }

    public void voteFor(int restaurantId, int userId) {
        LOG.info("user with id={}, voted for restaurant={}", userId, restaurantId);
        service.voteFor(restaurantId, userId);
    }

    public void cancelChoice(int reststaurantId, int userId) {
        LOG.info("user with id={} cancel his vote for restaurant={}", userId, reststaurantId);
        service.cancelChoice(reststaurantId, userId);
    }

}
