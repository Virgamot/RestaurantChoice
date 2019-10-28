package ru.graduation.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.AuthorizedUser;
import ru.graduation.model.Restaurant;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(AdminRestaurantRestController.REST_URL)
public class AdminRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createNew(@Valid @RequestBody Restaurant restaurant, @AuthenticationPrincipal AuthorizedUser authUser) {

        Restaurant created = super.save(restaurant, authUser.getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{/id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int restaurantId,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        assureIdConsistent(restaurant, restaurantId);
        super.update(restaurant, authorizedUser.getId());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int restaurantId, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(restaurantId, authorizedUser.getId());
    }
}
