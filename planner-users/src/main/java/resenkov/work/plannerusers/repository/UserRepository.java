package resenkov.work.plannerusers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resenkov.work.plannerusers.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
