package resenkov.work.plannertodo.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import resenkov.work.plannertodo.entity.Task;
import resenkov.work.plannertodo.repo.TaskRepository;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll(Long userId) {
        return repository.findByUserIdOrderByTitleAsc(userId);
    }

    public Task add(Task task) {
        return repository.save(task);
    }

    public Task update(Task task) {
        return repository.save(task);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page<Task> findByParams(String text, Boolean completed, Long priorityId, Long categoryId, Long userId, Date dateFrom, Date dateTo, PageRequest paging) {
        return repository.findByParams(text, completed, priorityId, categoryId, userId, dateFrom, dateTo, paging);
    }

    public Task findById(Long id) {
        return repository.findById(id).get();
    }


}
