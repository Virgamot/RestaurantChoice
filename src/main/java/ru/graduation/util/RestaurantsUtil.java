package ru.graduation.util;

import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;

import java.util.*;

public class RestaurantsUtil {

    public static List<Restaurant> getAllWithDishes(List<Restaurant> restaurants, List<Dish> dishes) {
        Map<Integer, List<Dish>> restaurantsWithDishes = new HashMap<>();

        restaurants.forEach(r -> {
            restaurantsWithDishes.put(r.getId(), new ArrayList<>());
        });

        dishes.forEach(dish -> {
            int restaurantId = dish.getRestaurant().getId();
            restaurantsWithDishes.get(restaurantId).add(dish);
        });

        restaurants.forEach(r -> {
            r.setDishes(restaurantsWithDishes.get(r.getId()));
        });

        return restaurants;
    }


    //TODO
    public static Restaurant getWithDishes(Restaurant restaurant) {
        return null;
    }

}
