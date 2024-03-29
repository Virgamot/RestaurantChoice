package ru.graduation.repository;

import ru.graduation.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    default User getWithRestaurant(int id) {
        throw new UnsupportedOperationException();
    }
}
