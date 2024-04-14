package cl.sumativa2.sumativa2.services;

import cl.sumativa2.sumativa2.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getStudentById(Long id);
    User registerUser(User user);
    boolean deleteUser(Long id);
    User updateUser(Long id, User user);
}
