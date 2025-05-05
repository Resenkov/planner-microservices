package resenkov.work.plannerusers.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import resenkov.work.plannerusers.entity.User;
import resenkov.work.plannerusers.service.UserService;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long userId){
        return ResponseEntity.ok(userService.getById(userId));
    }

    @GetMapping("/get")
    public ResponseEntity<User> getByEmail(@RequestBody String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
