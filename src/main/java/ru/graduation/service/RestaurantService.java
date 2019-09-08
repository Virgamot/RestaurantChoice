package ru.graduation.service;

import ru.graduation.model.Restaurant;

import java.util.Collection;

public interface RestaurantService {
    Restaurant get(int id);
    void delete(int id, int userId);
    Collection<Restaurant> getAll();
    Restaurant update(Restaurant restaurant, int userId);
    Restaurant save(Restaurant restaurant, int userId);
    Restaurant getWithDishes(int id);
}
