package ru.graduation.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant {
    private String address;
    private List<Dish> dishes;

    public Restaurant(String address, Dish... dishes) {
        this.address = address;
        this.dishes = Arrays.asList(dishes);
    }

    public String getAddress() {
        return address;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
