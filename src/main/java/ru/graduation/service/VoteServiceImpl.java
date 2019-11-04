package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.util.exception.IllegalRequestDataException;

import java.time.LocalTime;

import static ru.graduation.util.TimeUtil.checkTime;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public void voteFor(int restaurantId, int userId, LocalTime currentTime) {
        User user = userRepository.get(userId);

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
    @Transactional
    public void cancelChoice(int restaurantId, int userId, LocalTime currentTime) {
        checkTime(currentTime);
        User user = userRepository.get(userId);
        if (user.getRestaurant() == null || user.getRestaurant().getId() != restaurantId) {
            String errorMsg = String.format("User with id=%d, didn't vote for the restaurant with id=%d", userId, restaurantId);
            throw new IllegalRequestDataException(errorMsg);
        }
        restaurantRepository.decreaseRating(restaurantId);
        user.setRestaurant(null);
        userRepository.save(user);
    }
}
