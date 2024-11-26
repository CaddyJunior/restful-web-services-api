package com.learning.webservices.restful_web_services.user.service;

import com.learning.webservices.restful_web_services.user.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int count = 0;

    static {
        users.add(new User(++count, "Nal", LocalDate.now().minusYears(30)));
        users.add(new User(++count, "Percy", LocalDate.now().minusYears(25)));
        users.add(new User(++count, "Buhle", LocalDate.now().minusYears(21)));
        users.add(new User(++count, "Nana", LocalDate.now().minusYears(18)));
        users.add(new User(++count, "KB", LocalDate.now().minusYears(27)));
    }


    public List<User> findAll() {
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User addUser(User user){
        user.setId(++count);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
