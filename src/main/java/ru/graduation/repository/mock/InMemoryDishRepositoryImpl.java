package ru.graduation.repository.mock;

import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.util.DishUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDishRepositoryImpl implements DishRepository {

    private Map<Integer, Dish> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        DishUtil.DISHES.forEach(d -> {
            repository.put(d.getId(), d);
        });
    }

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            dish.setId(counter.incrementAndGet());
            repository.put(dish.getId(), dish);
            return dish;
        } else {
            repository.put(dish.getId(), dish);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Dish get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Dish> getAll() {
        return new ArrayList<>(repository.values());
    }
}
