package resenkov.work.plannertodo.controller;



import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.plannerentity.entity.Categories;
import resenkov.work.plannertodo.search.CategorySearchValues;
import resenkov.work.plannertodo.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/category") // базовый URI
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/all")
    public List<Categories> findAll(@RequestBody Long userId) {
        return categoryService.findAll(userId);
    }




    @PostMapping("/add")
    public ResponseEntity<Categories> add(@RequestBody Categories categories) {

        if(categories.getTitle() == null || categories.getTitle().trim().length()== 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(categories));
    }

    @PutMapping("/update")
    public ResponseEntity<Categories> update(@RequestBody Categories category){
        if(category.getId() == null || category.getId() == 0){
            return new ResponseEntity("redunant param: ID MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null || category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try{
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("Id " + id + " not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Categories>> search(@RequestBody CategorySearchValues searchValues) {
        if(searchValues.getUserId() == null || searchValues.getUserId() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }
        List<Categories> list = categoryService.findByTitle(searchValues.getTitle(), searchValues.getUserId());
        return ResponseEntity.ok(list);
    }


    @PostMapping("/id")
    public ResponseEntity<Categories> findById(@RequestBody Long id) {
        Categories categories = null;

        try{
            categories = categoryService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("Id " + id + " not found",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categories);
    }
}
