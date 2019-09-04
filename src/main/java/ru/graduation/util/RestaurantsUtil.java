package ru.graduation.util;

import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;

import java.util.*;

import static ru.graduation.util.DishUtil.DISHES;

public class RestaurantsUtil {

    public static Restaurant RESTAURANT_1 = new Restaurant(100001, "St.Petersburg, Leninskiy Prospect st., 21");
    public static Restaurant RESTAURANT_2 = new Restaurant(100002, "St.Petersburg, Panfilova st., 87");

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

    public static void main(String[] args) {
        getAllWithDishes(Arrays.asList(RESTAURANT_1, RESTAURANT_2), DISHES).forEach(r -> {
            System.out.println("Restaurant adders: " + r.getAddress() + "\n" +
                    "With dishes: \n" + DishUtil.dishesToString(r.getDishes()));
        });
    }

}
