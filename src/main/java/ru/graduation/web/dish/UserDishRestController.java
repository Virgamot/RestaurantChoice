package ru.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.to.DishTo;

import java.util.List;

@RestController
@RequestMapping(value = UserDishRestController.REST_URL)
public class UserDishRestController {

    static final String REST_URL = "/rest/dishes";

    private static final Logger LOG = LoggerFactory.getLogger(UserDishRestController.class);

    @Autowired
    private DishRepository repository;

    @GetMapping("/{id}")
    public DishTo get(@PathVariable("id") int id) {
        LOG.info("get dish {}", id);
        return new DishTo(repository.get(id));
    }

    @GetMapping
    public List<Dish> getAll() {
        LOG.info("getAll() dishes");
        return repository.getAll();
    }

}
