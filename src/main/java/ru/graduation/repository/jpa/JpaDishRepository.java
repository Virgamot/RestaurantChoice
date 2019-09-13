package ru.graduation.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaDishRepository implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        Dish dish = em.find(Dish.class, id);
        return dish;
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL).getResultList();
    }
}
