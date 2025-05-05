package resenkov.work.plannerusers.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import resenkov.work.plannerusers.entity.User;
import resenkov.work.plannerusers.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User getById(Long userId){
        return userRepository.getById(userId);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }
}
