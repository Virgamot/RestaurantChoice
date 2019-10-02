package ru.graduation.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Dish;
import ru.graduation.repository.AbstractRepositoryTest;
import ru.graduation.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.graduation.DishTestData.*;

class DataJpaDishRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private DishRepository repository;

    @Test
    void save() {
        Dish created = getCreated();
        repository.save(created);
        assertMatch(repository.get(created.getId()), created);
    }

    @Test
    void delete() {
        List<Dish> listWithDeleted = new ArrayList<>(DISHES);
        repository.delete(DISH1_ID);
        listWithDeleted.remove(DISH_1);
        assertMatch(repository.getAll(), listWithDeleted);
    }

    @Test
    void get() {
        Dish dish = repository.get(DISH1_ID);
        assertMatch(dish, DISH_1);
    }

    @Test
    void getAll() {
        List<Dish> dishes = repository.getAll();
        assertMatch(dishes, DISHES);
    }
}