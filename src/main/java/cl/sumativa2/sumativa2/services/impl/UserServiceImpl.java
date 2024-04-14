package cl.sumativa2.sumativa2.services.impl;

import cl.sumativa2.sumativa2.models.User;
import cl.sumativa2.sumativa2.repository.UserRepository;
import cl.sumativa2.sumativa2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getStudentById(Long id) {
        return null;
    }

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }
}
