package ru.graduation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.repository.JpaUtil;
import ru.graduation.util.exception.NotFoundException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.graduation.RestaurantTestData.RESTAURANT_1;
import static ru.graduation.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void testCreate() throws Exception {
        User newUser = getCreated();
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), USER, ADMIN, created);
    }

    @Test
    void testDuplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER)));
    }


    @Test
    void testDelete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    void testGetNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }


    @Test
    void getByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);

    }

    @Test
    void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, USER, ADMIN);
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    void getWithRestaurant() {
        User created = getCreated();
        created.setRestaurant(RESTAURANT_1);
        service.create(created);
        User user = service.getWithRestaurant(created.getId());
        RestaurantTestData.assertMatch(user.getRestaurant(), RESTAURANT_1);
    }
}