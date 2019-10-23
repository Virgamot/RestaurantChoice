package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;

import java.time.LocalTime;

import static ru.graduation.util.TimeUtil.checkTime;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void voteFor(int restaurantId, int userId, LocalTime currentTime) {
        User user = userRepository.getWithRestaurant(userId);

        Restaurant restaurant = user.getRestaurant();

        if (restaurant != null) {
            checkTime(currentTime);
            restaurantRepository.decreaseRating(restaurant.getId());
        }

        restaurantRepository.increaseRating(restaurantId);

        user.setRestaurant(restaurantRepository.getReference(restaurantId));
        userRepository.save(user);
    }

    @Override
    public void cancelChoice(int restaurantId, int userId, LocalTime currentTime) {
        checkTime(currentTime);

        User user = userRepository.get(userId);

        if (user.getRestaurant() == null || user.getRestaurant().getId() != restaurantId) {
            //TODO custom exception
            throw new IllegalArgumentException();
        }
        restaurantRepository.decreaseRating(restaurantId);
        user.setRestaurant(null);
        userRepository.save(user);
    }
}
