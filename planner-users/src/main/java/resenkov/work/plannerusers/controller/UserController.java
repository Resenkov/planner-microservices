package resenkov.work.plannerusers.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import resenkov.work.plannerusers.entity.User;
import resenkov.work.plannerusers.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUserByEmail(@RequestBody String email){
        userService.deleteUserByEmail(email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<User> getById(@RequestBody Long userId){
        Optional<User> userOptional = userService.findById(userId);
        try {
            if(userOptional.isPresent()){
                return ResponseEntity.ok(userOptional.get());
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get")
    public ResponseEntity<User> getByEmail(@RequestBody String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
