package ru.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.graduation.AuthorizedUser;
import ru.graduation.service.VoteService;

import java.time.LocalTime;

@RestController
@RequestMapping(value = VoteRestController.REST_URL)
public class VoteRestController {
    final static String REST_URL = "/rest/vote/";

    @Autowired
    private VoteService service;

    @PutMapping(value = "/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void voteFor(@PathVariable("restaurantId") int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser,
                        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam("time") LocalTime time) {
        service.voteFor(restaurantId, authUser.getId(), time);
    }

    @PatchMapping(value = "/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelChoice(@PathVariable("restaurantId") int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam("time") LocalTime time) {
        service.cancelChoice(restaurantId, authUser.getId(), time);
    }


}
