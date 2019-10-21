package ru.graduation.service;

import java.time.LocalTime;

public interface VoteService {

    void voteFor(int restaurantId, int userId, LocalTime currentTime);

    void cancelChoice(int restaurantId, int userId, LocalTime currentTime);
}
