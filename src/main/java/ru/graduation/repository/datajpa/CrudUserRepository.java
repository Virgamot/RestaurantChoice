package ru.graduation.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    List<User> findAll(Sort sort);

    @Transactional
    @Modifying
    @Override
    User save(User user);


    User getByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.restaurant WHERE u.id=:id")
    User getWithRestaurant(@Param("id") int id);
}
