package resenkov.work.plannertodo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resenkov.work.plannerentity.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findByUserIdOrderByTitleAsc(Long userId);

    @Query("SELECT  p from Priority p where " +
    "(:title is null or :title =' ' "+
    "or lower(p.title) like(concat('%', :title, '%'))) " +
            "and  p.userId = :userId "+
            "order by p.title asc")
    List<Priority> findByTitle(@Param("title") String title, @Param("userId") Long userId);

}
