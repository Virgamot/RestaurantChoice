package ru.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository repository;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
