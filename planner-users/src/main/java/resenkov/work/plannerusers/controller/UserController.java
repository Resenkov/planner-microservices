package resenkov.work.plannerusers.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import resenkov.work.plannerusers.entity.User;
import resenkov.work.plannerusers.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final String TOPIC_NAME = "resenkov-test";
    private final UserService userService;
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public UserController(UserService userService, KafkaTemplate kafkaTemplate) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(user.getPassword() == null || user.getPassword().trim().length() == 0){
            return new ResponseEntity("Пароль не должен быть пустым",HttpStatus.NOT_ACCEPTABLE);
        }
        if(user.getUsername() == null || user.getUsername().trim().length() == 0){
            return new ResponseEntity("Имя пользователя не должно быть пустым", HttpStatus.NOT_ACCEPTABLE);
        }
        user = userService.addUser(user);


        if(user != null){
            kafkaTemplate.send(TOPIC_NAME, user.getId());
        }

        return ResponseEntity.ok(user);
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
