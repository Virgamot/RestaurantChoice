package ru.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;
import static ru.graduation.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Lenin", "St.Petersburg, Leninskiy Prospect st., 21");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT2_ID, "Rain","St.Petersburg, Panfilova st., 87");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2);

    public static Restaurant getCreated() {
        return new Restaurant(null, "CreatedRestaurant", "CreatedAddress");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "rating");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... excpected) {
        assertMatch(actual, Arrays.asList(excpected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "dishes"));
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "dishes"));
    }
}
