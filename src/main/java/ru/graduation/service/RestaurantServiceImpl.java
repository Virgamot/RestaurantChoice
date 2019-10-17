package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    //TODO
    @Override
    public void delete(int restaurantId, int userId) {
        restaurantRepository.delete(restaurantId);
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

    @Override
    public void voteFor(int restaurantId, int userId) {
        User user = userRepository.getWithRestaurant(userId);
        Restaurant userRestaurant = user.getRestaurant();
        if (userRestaurant != null) {
            //TODO time-check
            restaurantRepository.decreaseRating(userRestaurant.getId());
        }

        restaurantRepository.increaseRating(restaurantId);

        user.setRestaurant(restaurantRepository.getReference(restaurantId));
        userRepository.save(user);
    }

    @Override
    public void cancelChoice(int restaurantId, int userId) {

        User user = userRepository.getWithRestaurant(userId);

        if (user.getRestaurant()==null||user.getRestaurant().getId() != restaurantId) {
            //TODO custom exception
            throw new IllegalArgumentException();
        }
        restaurantRepository.decreaseRating(restaurantId);
        user.setRestaurant(null);
        userRepository.save(user);
    }

    //TODO
    @Override
    public Restaurant getWithDishes(int id) {
        return restaurantRepository.getWithDishes(id);
    }
}
