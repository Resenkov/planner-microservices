package resenkov.work.plannertodo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import resenkov.work.plannertodo.entity.Stat;


@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
    Stat findByUserId(Long userId);
}
