package ru.graduation.repository;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant getReference(int id);

    List<Restaurant> getAll();

    void increaseRating(int id);

    void decreaseRating(int id);

    Restaurant get(int id);

    Restaurant getWithDishes(int id);
}
