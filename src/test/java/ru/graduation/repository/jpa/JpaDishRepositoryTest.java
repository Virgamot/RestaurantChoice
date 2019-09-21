package ru.graduation.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.graduation.DishTestData.*;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class JpaDishRepositoryTest {

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