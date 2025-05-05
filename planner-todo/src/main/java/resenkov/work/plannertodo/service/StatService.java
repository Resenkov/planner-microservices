package resenkov.work.plannertodo.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import resenkov.work.plannertodo.entity.Stat;
import resenkov.work.plannertodo.repo.StatRepository;


@Service
@Transactional
public class StatService {

    private final StatRepository repository; // сервис имеет право обращаться к репозиторию (БД)

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(Long userId) {
        return repository.findByUserId(userId);
    }

}