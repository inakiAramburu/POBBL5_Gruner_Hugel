package gruner.huger.grunerhugel.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gruner.huger.grunerhugel.model.User;

@Service
public class UserDaoMySQL implements UserDao{

    @Autowired
    private UserRepository userRepository;

    public UserDaoMySQL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
    
}
