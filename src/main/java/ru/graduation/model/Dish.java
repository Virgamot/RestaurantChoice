package ru.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d")
})


@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    public static final String DELETE = "Dish.delete";
    public static final String GET_ALL = "Dish.getAll";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "price", nullable = false)
    @NotEmpty
    private Double price;

    public Dish(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public Dish(Restaurant restaurant, String description, Double price) {
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
    }

    public Dish(Integer id, Restaurant restaurant, String description, Double price) {
        super(id);
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
