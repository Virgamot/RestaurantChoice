package ru.graduation.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r")
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String GET_ALL = "Restaurant.getAll";

    @Column(name = "address", nullable = false)
    @NotEmpty
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> dishes;

    @Column(name = "rating", nullable = false)
    @Digits(integer = 10, fraction = 0)
    @Range(min = 0)
    private int rating;

    public Restaurant() {
    }

    public Restaurant(Integer id, String address, Dish... dishes) {
        this.address = address;
        this.dishes = Arrays.asList(dishes);
        this.setId(id);
    }

    public String getAddress() {
        return address;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void incrementRating() {
        this.rating++;
    }

    public void decrementRating() {
        this.rating--;
    }
}
