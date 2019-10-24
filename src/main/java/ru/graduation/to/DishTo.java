package ru.graduation.to;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;

public class DishTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @SafeHtml
    private String description;

    @NotEmpty
    private Double price;

    private int restaurantId;

    public DishTo() {
    }

    public DishTo(Integer id, String description, Double price, int restaurantId) {
        super(id);
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
