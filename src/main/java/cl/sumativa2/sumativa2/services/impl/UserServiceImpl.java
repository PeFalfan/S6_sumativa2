package cl.sumativa2.sumativa2.services.impl;

import cl.sumativa2.sumativa2.models.LogInModel;
import cl.sumativa2.sumativa2.models.ResponseModel;
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
    public ResponseModel getAllUsers() {
        try {
            ResponseModel response = new ResponseModel();

            List<UserModel> userModels = repository.findAll();

            if (userModels.isEmpty()) {
                response.setMessageResponse("No se encontraron usuarios registrados");
                response.setData(null);
                response.setError(null);

                return response;
            }

            response.setMessageResponse("Se encuentran: " + userModels.size() + " usuarios registrados");
            response.setData(userModels);
            response.setError(null);

            return response;

        } catch (Exception e){
            ResponseModel response = new ResponseModel();
            response.setError(e.toString());
            response.setData(null);
            response.setMessageResponse(e.getMessage());

            return response;
        }
    }

    @Override
    public ResponseModel getUserByEmail(String email){
        try {
            ResponseModel response = new ResponseModel();
            List<UserModel> userModels = repository.findAll();

            for (UserModel userModel : userModels) {
                if (userModel.getEmail().equals(email)) {
                    response.setData(userModel);
                    response.setError(null);
                    response.setMessageResponse("Usuario encontrado");
                    return response;
                }
            }
            response.setMessageResponse("No se encontraron usuarios registrados");
            response.setData(null);
            response.setError("No existe el usuario con email " + email);

            return response;
        } catch (Exception e){
            ResponseModel response = new ResponseModel();
            response.setError(e.toString());
            response.setData(null);
            response.setMessageResponse(e.getMessage());
            return response;
        }
    }

    @Override
    public ResponseModel registerUser(UserModel userModel){
        try {
            ResponseModel response = new ResponseModel();

            List<UserModel> userModels = repository.findAll();

            for(UserModel u : userModels) {
                if(u.getEmail().equals(userModel.getEmail())) {
                    response.setMessageResponse("error al registrar al usuario");
                    response.setData(null);
                    response.setError("Email ya registrado");

                    return response;
                }
            }

            UserModel savedUser = repository.save(userModel);

            response.setData(savedUser);
            response.setError(null);
            response.setMessageResponse("Usuario: " + savedUser.getUserName() + " registrado exitosamente");

            return response;

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("error al registrar al usuario");

            return response;
        }
    }

    @Override
    public ResponseModel deleteUser(Long id){
        try {
            ResponseModel response = new ResponseModel();

            List<UserModel> userModels = repository.findAll();

            for(UserModel u : userModels) {
                if(u.getId().equals(id)) {
                    repository.delete(u);
                    response.setData(true);
                    response.setError(null);
                    response.setMessageResponse("Usuario eliminado");
                    return response;
                }
            }

            response.setMessageResponse("Error al eliminar el usuario");
            response.setData(null);
            response.setError("No existe el usuario con id " + id);

            return response;

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setError(e.toString());
            response.setData(null);
            response.setMessageResponse(e.getMessage());
            return response;
        }
    }

    @Override
    public ResponseModel updateUser(Long id, UserModel userModel){
        try {
            ResponseModel response = new ResponseModel();

            if(repository.existsById(id)) {
                userModel.setId(id);
                response.setData(repository.save(userModel));
                response.setError(null);
                response.setMessageResponse("Usuario actualizado correctamente");
            } else {
                response.setMessageResponse("Error al actualizar el usuario");
                response.setData(null);
                response.setError("No existe el usuario con id " + id);
            }

            return response;
        } catch (Exception e){
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al actualizar el usuario");

            return response;
        }
    }

    @Override
    public ResponseModel loginUser(LogInModel logIn) {
        try {
            ResponseModel response = new ResponseModel();

            List<UserModel> userModels = repository.findAll();

            for (UserModel userModel : userModels) {
                if(userModel.getEmail().equals(logIn.getEmail())) {
                    if(userModel.getPassword().equals(logIn.getPassword())) {
                        response.setData(userModel);
                        response.setError(null);
                        response.setMessageResponse("Credenciales validadas correctamente");

                        return response;
                    }
                    response.setData(null);
                    response.setError("Contraseña incorrecta");
                    response.setMessageResponse("Error al ingresar");
                    return response;
                }
            }
            response.setData(null);
            response.setError("Credenciales Incorrectas");
            response.setMessageResponse("Error al ingresar");
            return response;

        }catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al ingresar");
            return response;
        }
    }


}
