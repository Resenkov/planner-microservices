package resenkov.work.plannertodo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import resenkov.work.plannertodo.entity.Priority;
import resenkov.work.plannertodo.repo.PriorityRepository;


import java.util.List;


@Service
@Transactional

public class PriorityService {

    PriorityRepository priorityRepository;
    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> findAll(Long userId) {
        return priorityRepository.findByUserIdOrderByTitleAsc(userId);
    }

    public Priority add(Priority priority) {
        return priorityRepository.save(priority);
    }

    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }

    public Priority update(Priority priority) {
        return priorityRepository.save(priority);
    }

    public List<Priority> findByTitle(String title, Long userId) {
        return priorityRepository.findByTitle(title, userId);
    }

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

}