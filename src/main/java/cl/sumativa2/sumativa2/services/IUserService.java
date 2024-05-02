package cl.sumativa2.sumativa2.services;

import cl.sumativa2.sumativa2.models.LogInModel;
import cl.sumativa2.sumativa2.models.ResponseModel;
import cl.sumativa2.sumativa2.models.UserModel;

import java.util.List;

public interface IUserService {

    ResponseModel getAllUsers();
    ResponseModel getUserByEmail(String email);
    ResponseModel registerUser(UserModel userModel);
    ResponseModel deleteUser(Long id);
    ResponseModel updateUser(Long id, UserModel userModel);
    ResponseModel loginUser(LogInModel logIn);
}
