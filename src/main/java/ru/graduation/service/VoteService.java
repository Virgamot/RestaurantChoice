package ru.graduation.service;

public interface VoteService {

    void voteFor(int restaurantId, int userId);

    void cancelChoice(int restaurantId, int userId);
}
