package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.Collection;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    //TODO
    @Override
    public void delete(int id, int userId) {
        repository.delete(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return repository.getAll();
    }

    //TODO
    @Override
    public Restaurant update(Restaurant restaurant, int userId) {
        return repository.save(restaurant);
    }

    //TODO
    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return repository.save(restaurant);
    }

    //TODO
    @Override
    public Restaurant getWithDishes(int id) {
        return null;
    }
}
