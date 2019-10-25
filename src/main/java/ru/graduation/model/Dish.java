package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d")
})

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    static final String DELETE = "Dish.delete";
    static final String GET_ALL = "Dish.getAll";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @Positive
    private Double price;

    public Dish() {
    }

    public Dish(Integer id, String name, Double price) {
        this(id, name, price, null);
    }

    public Dish(Integer id, String name, Double price, Restaurant restaurant) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
    }

    public String getDescription() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
