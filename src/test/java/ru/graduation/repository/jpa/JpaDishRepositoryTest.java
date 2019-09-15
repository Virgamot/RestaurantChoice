package ru.graduation.repository.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import static org.junit.jupiter.api.Assertions.*;
import static ru.graduation.DishTestData.DISH1_ID;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
class JpaDishRepositoryTest {

    @Autowired
    private DishRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
        Dish dish=repository.get(DISH1_ID);
        System.out.println(dish);
    }

    @Test
    void getAll() {
    }
}