package resenkov.work.plannertodo.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resenkov.work.plannerentity.entity.Categories;
import resenkov.work.plannertodo.repo.CategoryRepository;

import java.util.List;

@Service

@Transactional
public class CategoryService {

    private final CategoryRepository repository; // сервис имеет право обращаться к репозиторию (БД)


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Categories> findAll(Long userId) {
        return repository.findByUserIdOrderByTitleAsc(userId);
    }

    public Categories add(Categories category) {
        return repository.save(category);
    }

    public Categories update(Categories category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Categories> findByTitle(String title, Long userId) {
        return repository.findByTitle(title, userId);
    }

    public Categories findById(Long id) {
        return repository.findById(id).get();
    }
}