package ru.graduation.service;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    void delete(int id, int userId);

    List<Restaurant> getAll();

    Restaurant update(Restaurant restaurant, int userId);

    Restaurant save(Restaurant restaurant, int userId);

    Restaurant get(int id);

    Restaurant getWithDishes(int id);
}
