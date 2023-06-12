package ru.shlokov.UserRequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.shlokov.UserRequest.model.Request;
import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.model.enums.RequestState;
import ru.shlokov.UserRequest.model.enums.Role;
import ru.shlokov.UserRequest.service.RequestService;
import ru.shlokov.UserRequest.service.UserService;

/**
 * @author Shlokov Andrey
 */
@RestController
@RequestMapping(path = "/request")
public class RequestController {
    private final RequestService requestService;
    private final UserService userService;

    @Autowired
    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody String text) {
        if (text == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!checkAuthority(Role.user))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return requestService.create(text, getUser().getEmail())
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        if (checkAuthority(Role.user)) {
            return new ResponseEntity<>(requestService.showAllSorted(getUser().getEmail()), HttpStatus.OK);
        }
        if (checkAuthority(Role.operator)) {
            return new ResponseEntity<>(requestService.showAllSorted(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Request request) {
        return (request.getState() == RequestState.draft && checkAuthority(Role.user))
                ? new ResponseEntity<>(requestService.edit(request), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/edit/state")
    public ResponseEntity<HttpStatus> setRequestState(@RequestBody Request request, @RequestParam RequestState state) {
        if (checkAuthority(Role.user)) {
            return requestService.changeState(request, RequestState.sent)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (checkAuthority(Role.operator)) {
            return requestService.changeState(request, state)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/send")
    public ResponseEntity<HttpStatus> send(@RequestBody Request request) {
        if (request.getState() != RequestState.draft)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        request.setState(RequestState.sent);
        requestService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkAuthority(Role role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(role);
    }

    private User getUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

