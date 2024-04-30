package cl.sumativa2.sumativa2.controllers;

import cl.sumativa2.sumativa2.models.LogInModel;
import cl.sumativa2.sumativa2.models.ResponseModel;
import cl.sumativa2.sumativa2.models.UserModel;
import cl.sumativa2.sumativa2.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseModel getAllUsers() {
        try {
            ResponseModel response = new ResponseModel();

            log.info("GET /users");
            log.info("Retornando todos los usuarios");

            List<UserModel> userModels = userService.getAllUsers();

            if (userModels == null || userModels.isEmpty()) {
                response.setMessageResponse("No se encontraron usuarios registrados");
                response.setData(null);
                response.setError(null);

                return response;
            }
            response.setMessageResponse("Se encuentran: " + userModels.size() + " usuarios registrados");

            List<EntityModel<UserModel>> userResources = userModels.stream()
                            .map(user -> EntityModel.of(user,
                                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserByEmail(user.getEmail())).withSelfRel())).toList();

            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

            CollectionModel<EntityModel<UserModel>> resources = CollectionModel.of(userResources, linkTo.withRel("users"));


            response.setData(resources);
            response.setError(null);

            return response;

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setError(e.toString());
            response.setData(null);
            response.setMessageResponse(e.getMessage());

            return response;
        }
    }

    @PutMapping(value = "/registerUser")
    public ResponseModel registerUser(@RequestBody UserModel userModel) {
        try {
            ResponseModel response = new ResponseModel();
            UserModel registeredUserModel = userService.registerUser(userModel);

            response.setData(registeredUserModel);
            response.setError(null);
            response.setMessageResponse("Usuario " + "registrado exitosamente");

            return response;
        }catch (Exception e){
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("error al registrar al usuario");

            return response;
        }
    }

    @GetMapping(value = "/getUserByEmail{email}")
    public ResponseModel getUserByEmail(@RequestParam(value = "email") String email) {
        try {
            ResponseModel response = new ResponseModel();

            UserModel userModel = userService.getUserByEmail(email);
            response.setData(userModel);
            response.setError(null);
            response.setMessageResponse("Usuario " + userModel.getUserName()+ " cargado exitosamente");
            return response;
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("error al obtener el usuario");
            return response;
        }
    }

    @DeleteMapping(value = "/deleteUserById{id}")
    public ResponseModel deleteUserById(@RequestParam(value = "id") Long id) {
        try {
            ResponseModel response = new ResponseModel();
            boolean result = userService.deleteUser(id);
            response.setData(result);
            response.setError(null);
            response.setMessageResponse("Usuario eliminado correctamente");

            return response;

        }catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al eliminar el usuario");

            return response;
        }
    }

    @PutMapping(value = "/updateUser{id}")
    public ResponseModel updateUser(@RequestParam(value = "id") Long id, @RequestBody UserModel userModel) {
        try {
            ResponseModel response = new ResponseModel();

            UserModel updatedUserModel = userService.updateUser(id, userModel);
            response.setData(updatedUserModel);
            response.setError(null);
            response.setMessageResponse("Usuario actualizado correctamente");

            return response;

        }catch (Exception e){
            ResponseModel response = new ResponseModel();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al actualizar el usuario");

            return response;
        }
    }

    @PostMapping(value = "/logIn")
    public ResponseModel logIn(@RequestBody LogInModel logIntent) {
        try {
            ResponseModel response = new ResponseModel();
            boolean result = userService.loginUser(logIntent);
            response.setData(result);
            response.setError(null);
            response.setMessageResponse("Credenciales validadas correctamente");

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
