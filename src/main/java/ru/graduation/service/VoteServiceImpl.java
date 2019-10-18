package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;

public class VoteServiceImpl implements VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

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

        if (user.getRestaurant() == null || user.getRestaurant().getId() != restaurantId) {
            //TODO custom exception
            throw new IllegalArgumentException();
        }
        restaurantRepository.decreaseRating(restaurantId);
        user.setRestaurant(null);
        userRepository.save(user);
    }
}
