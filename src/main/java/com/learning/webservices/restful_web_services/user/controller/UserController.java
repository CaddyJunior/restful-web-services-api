package com.learning.webservices.restful_web_services.user.controller;

import com.learning.webservices.restful_web_services.user.exceptions.UserNotFoundException;
import com.learning.webservices.restful_web_services.user.model.User;
import com.learning.webservices.restful_web_services.user.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    public UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAll(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user== null)
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@Valid @PathVariable int id){
        User user = service.findOne(id);
        if (user== null)
            throw new UserNotFoundException("id:"+id);

        service.deleteById(id);
    }

}
