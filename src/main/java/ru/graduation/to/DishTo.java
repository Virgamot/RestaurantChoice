package ru.graduation.to;

import org.hibernate.validator.constraints.SafeHtml;
import ru.graduation.model.Dish;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DishTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @SafeHtml
    private String name;

    @NotEmpty
    private Double price;

    private int restaurantId;

    public DishTo() {
    }

    public DishTo(Dish dish) {
        this(dish.getId(), dish.getDescription(), dish.getPrice(), dish.getRestaurant().getId());
    }

    public DishTo(Integer id, String name, Double price, int restaurantId) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
