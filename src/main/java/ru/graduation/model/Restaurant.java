package ru.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {
    @Column(name = "address",nullable = false)
    @NotEmpty
    private String address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<Dish> dishes;

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
}
