package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE  FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant WHERE d.id=:id")
    Dish getWithRestaurant(@Param("id") int id);
}
