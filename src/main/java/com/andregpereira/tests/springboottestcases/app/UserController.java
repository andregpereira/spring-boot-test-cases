package com.andregpereira.tests.springboottestcases.app;

import com.andregpereira.tests.springboottestcases.domain.entities.User;
import com.andregpereira.tests.springboottestcases.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    private ResponseEntity<User> post(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        User userCreated = service.create(user);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(userCreated);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> get(@PathVariable Long id) {
        return ResponseEntity.ofNullable(service.findById(id));
    }

}
