package ru.graduation.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant extends BaseEntity {
    private String address;
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
