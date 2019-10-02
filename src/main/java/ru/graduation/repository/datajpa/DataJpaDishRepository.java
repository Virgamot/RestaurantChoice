package ru.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaDishRepository implements DishRepository {

    @Autowired
    private CrudDishRepository repository;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }
}
