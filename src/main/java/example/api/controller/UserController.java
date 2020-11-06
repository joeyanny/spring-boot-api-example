package example.api.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.api.dao.UserRepository;
import example.api.model.User;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> get(@PathVariable("id") long id) throws SQLException {
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public void save(@RequestBody User user) throws SQLException {
        userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public void update(@PathVariable("id") long id, @RequestBody User user) throws SQLException {
        user.setId(id);
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") long id) throws SQLException {
        userRepository.deleteById(id);
    }
}
