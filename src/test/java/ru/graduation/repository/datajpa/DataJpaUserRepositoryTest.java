package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.UserRepository;

import java.util.List;

import static ru.graduation.RestaurantTestData.RESTAURANT_1;
import static ru.graduation.UserTestData.*;

class DataJpaUserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void testSave() throws Exception {
        User newUser = getCreated();
        User created = repository.save(newUser);
        newUser.setId(created.getId());
        assertMatch(repository.getAll(), USER, ADMIN, created);
    }

    @Test
    void testDelete() throws Exception {
        repository.delete(USER_ID);
        assertMatch(repository.getAll(), ADMIN);
    }

    @Test
    void testGet() throws Exception {
        User user = repository.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    void testGetByEmail() throws Exception {
        User user = repository.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    void testGetAll() throws Exception {
        List<User> users = repository.getAll();
        assertMatch(users, USER, ADMIN);
    }

    //TODO
    @Test
    void testGetWithRestaurant() throws Exception {
        User created=new User(null, "New", "new@gmail.com", "new_password", Role.ROLE_USER);
        created.setRestaurant(RESTAURANT_1);
        repository.save(created);
        User user = repository.getWithRestaurant(created.getId());
        System.out.println(user.getRestaurant());
    }
}