package cl.sumativa2.sumativa2.services.impl;

import cl.sumativa2.sumativa2.models.LogInModel;
import cl.sumativa2.sumativa2.models.UserModel;
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
    public List<UserModel> getAllUsers() throws Exception {
        List<UserModel> userModels = repository.findAll();

        if (userModels.isEmpty()) {
            throw new Exception("No se encuentran usuarios registrados");
        }
        return repository.findAll();
    }

    @Override
    public UserModel getUserById(Long id) {
        return null;
    }

    @Override
    public UserModel getUserByEmail(String email) throws Exception{
        List<UserModel> userModels = repository.findAll();

        for (UserModel userModel : userModels) {
            if (userModel.getEmail().equals(email)) {
                return userModel;
            }
        }
        throw new Exception("No existe el usuario con email " + email);
    }

    @Override
    public UserModel registerUser(UserModel userModel) throws Exception{
        List<UserModel> userModels = repository.findAll();

        for(UserModel u : userModels) {
            if(u.getEmail().equals(userModel.getEmail())) {
                throw new Exception("Email ya registrado");
            }
        }

        return repository.save(userModel);
    }

    @Override
    public boolean deleteUser(Long id) throws Exception{

        List<UserModel> userModels = repository.findAll();

        for(UserModel u : userModels) {
            if(u.getId().equals(id)) {
                repository.delete(u);
                return true;
            }
        }

        throw new Exception("No existe el usuario con id " + id);
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel) throws Exception{
        if(repository.existsById(id)) {
            userModel.setId(id);
            return repository.save(userModel);
        }
        throw new Exception("No existe el usuario con id " + id);
    }

    @Override
    public boolean loginUser(LogInModel logIn) throws Exception {
        List<UserModel> userModels = repository.findAll();

        for (UserModel userModel : userModels) {
            if(userModel.getEmail().equals(logIn.getEmail())) {
                if(userModel.getPassword().equals(logIn.getPassword())) {
                    return true;
                }
                throw new Exception("Contraseña incorrecta");
            }
        }
        throw new Exception("Credenciales Incorrectas");
    }


}
