package resenkov.work.plannertodo.controller;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.plannertodo.entity.Priority;
import resenkov.work.plannertodo.search.PrioritySearchValues;
import resenkov.work.plannertodo.service.PriorityService;
import resenkov.work.plannerutils.rest.resttemplate.UserRestBuilder;
import resenkov.work.plannerutils.rest.webclient.UserWebClientBuilder;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityService priorityService;
    private final UserWebClientBuilder userWebClientBuilder;

    public PriorityController(PriorityService priorityService, UserWebClientBuilder userWebClientBuilder) {
        this.priorityService = priorityService;
        this.userWebClientBuilder = userWebClientBuilder;
    }

    @PostMapping("/all")
    public List<Priority> getAllPriority(@RequestBody Long userId) {
        return priorityService.findAll(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if(priority.getTitle()== null || priority.getTitle().trim().length() == 0){
            return new ResponseEntity("Title must be not empty", HttpStatus.NOT_ACCEPTABLE);
        }

        if (userWebClientBuilder.userExists(priority.getUserId())){
            return ResponseEntity.ok(priorityService.add(priority));
        }

        return new ResponseEntity ("Ошибка при добавлении приоритета.", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        if(priority.getTitle()== null || priority.getTitle().trim().length() == 0){
            return new ResponseEntity("Title must be not empty", HttpStatus.NOT_ACCEPTABLE);
        }

        if(userWebClientBuilder.userExists(priority.getUserId())) {
            return ResponseEntity.ok(priorityService.update(priority));
        }

        return new ResponseEntity ("Ошибка при обновлении приоритета.", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deletePriority(@PathVariable Long id) {
        try {
            priorityService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<List<Priority>> searchPriority(@RequestBody PrioritySearchValues prioritySearchValues) {
        if(prioritySearchValues.getUserId() == null || prioritySearchValues.getUserId() == 0){
            return new ResponseEntity("Email must be not empty", HttpStatus.NOT_ACCEPTABLE);
        }
        List<Priority> list = priorityService.findByTitle(prioritySearchValues.getTitle(), prioritySearchValues.getUserId());
        return  ResponseEntity.ok(list);
    }

    @PostMapping("/id")
    public ResponseEntity<Priority> getPriorityById(@RequestBody Long id) {

        Priority priority = null;
        try {
            priority = priorityService.findById(id);
        }catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }
}
