package ru.graduation;

import ru.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "St.Petersburg, Leninskiy Prospect st., 21");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT2_ID, "St.Petersburg, Panfilova st., 87");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Moscow, Sportivnaya st., 13");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... excpected) {
        assertMatch(actual, Arrays.asList(excpected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes").isEqualTo(expected);
    }
}
