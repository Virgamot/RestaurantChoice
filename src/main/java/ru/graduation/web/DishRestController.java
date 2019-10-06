package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    private static final Logger LOG = LoggerFactory.getLogger(DishRestController.class);

    static final String REST_URL = "/rest/dishes";

    @Autowired
    private DishRepository repository;

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        LOG.info("get dish {}", id);
        return repository.get(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        LOG.info("getAll() dishes");
        return repository.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish save(@RequestBody Dish dish) {
        LOG.info("save {}", dish);
        return repository.save(dish);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete dish {}", id);
        repository.delete(id);
    }

}
