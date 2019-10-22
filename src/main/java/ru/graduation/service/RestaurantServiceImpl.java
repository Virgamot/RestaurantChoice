package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    //TODO
    @Override
    public void delete(int restaurantId, int userId) {
        restaurantRepository.delete(restaurantId);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    //TODO
    @Override
    public Restaurant update(Restaurant restaurant, int userId) {
        return restaurantRepository.save(restaurant);
    }

    //TODO
    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return restaurantRepository.save(restaurant);
    }

    //TODO
    @Override
    public Restaurant getWithDishes(int id) {
        return restaurantRepository.getWithDishes(id);
    }
}
