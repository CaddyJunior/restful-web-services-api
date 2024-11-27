package com.learning.webservices.restful_web_services.user.controller;

import com.learning.webservices.restful_web_services.user.exceptions.UserNotFoundException;
import com.learning.webservices.restful_web_services.user.model.Post;
import com.learning.webservices.restful_web_services.user.model.User;
import com.learning.webservices.restful_web_services.user.repository.PostRepository;
import com.learning.webservices.restful_web_services.user.repository.UserRepository;
import com.learning.webservices.restful_web_services.user.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class JpaUserController {

    public UserRepository userRepository;
    public PostRepository postRepository;

    public JpaUserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAll(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@Valid @PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());
        Post saved = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts/{postId}")
    public EntityModel<Post> getPostByUserId(@PathVariable int id, @PathVariable int postId){
        Optional<User> user = userRepository.findById(id);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        if (post.isEmpty())
            throw new UserNotFoundException("postId:"+postId);

        EntityModel<Post> entityModel = EntityModel.of(post.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUserPosts(id));
        entityModel.add(link.withRel("all-posts"));
        return entityModel;
    }

}
