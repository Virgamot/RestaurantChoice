package ru.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.to.DishTo;

import java.util.List;

import static ru.graduation.util.DishUtil.getFromTo;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminDishRestController.class);

    static final String REST_URL = "/rest/admin/dishes";

    @Autowired
    private DishRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish save(@RequestBody DishTo dishTo) {
        Dish dish = getFromTo(dishTo);
        LOG.info("save {}", dish);
        return repository.save(dish, dishTo.getRestaurantId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete dish {}", id);
        repository.delete(id);
    }

}
