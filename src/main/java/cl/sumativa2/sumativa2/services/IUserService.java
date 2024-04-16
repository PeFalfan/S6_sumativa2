package cl.sumativa2.sumativa2.services;

import cl.sumativa2.sumativa2.models.LogInModel;
import cl.sumativa2.sumativa2.models.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> getAllUsers() throws Exception;
    UserModel getUserByEmail(String email) throws Exception;
    UserModel registerUser(UserModel userModel) throws Exception;
    boolean deleteUser(Long id) throws Exception;
    UserModel updateUser(Long id, UserModel userModel) throws Exception;
    boolean loginUser(LogInModel logIn) throws Exception;
}
