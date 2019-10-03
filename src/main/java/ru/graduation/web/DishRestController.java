package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    private static final Logger LOG = LoggerFactory.getLogger(DishRestController.class);

    static final String REST_URL = "/rest/dishes";

    @Autowired
    private DishRepository repository;

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("id") int id) {
        LOG.info("get dish {}", id);
        return repository.get(id);
    }


}
