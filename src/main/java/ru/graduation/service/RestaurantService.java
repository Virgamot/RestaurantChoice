package ru.graduation.service;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant get(int id);

    void delete(int id, int userId);

    List<Restaurant> getAll();

    Restaurant update(Restaurant restaurant, int userId);

    Restaurant save(Restaurant restaurant, int userId);

    Restaurant getWithDishes(int id);

    void voteFor(int restaurantId, int userId);

    void cancelChoice(int restaurantId, int userId);
}
