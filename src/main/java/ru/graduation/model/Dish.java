package ru.graduation.model;

public class Dish extends BaseEntity {
    private String description;
    private Double price;

    public Dish(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
