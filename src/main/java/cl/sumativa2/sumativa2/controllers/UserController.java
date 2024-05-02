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

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    
    @GetMapping
    public ResponseModel getAllUsers() {

        log.info("GET /users");
        log.info("Retornando todos los usuarios");

        ResponseModel responseModel = userService.getAllUsers();

        if (responseModel.getData() != null) {
            // At this point, the unchecked cast warning is avoided because the element can only return this
            // class (List<UserModel>) or null, and the latter is being controlled
            @SuppressWarnings({"unchecked"})
            List<EntityModel<UserModel>> userResources = ((List<UserModel>) responseModel.getData()).stream()
                    .map(user -> EntityModel.of(user,
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                    .getUserByEmail(user.getEmail())).withSelfRel())).toList();

            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

            CollectionModel<EntityModel<UserModel>> resources =
                    CollectionModel.of(userResources, linkTo.withRel("users"));

            responseModel.setData(resources);
            responseModel.setError(null);
        }

        return responseModel;
    }

    @PutMapping(value = "/registerUser")
    public ResponseModel registerUser(@RequestBody UserModel userModel) {

        log.info("PUT /users/registerUser");
        log.info("Registrando al usuario");
        ResponseModel response = userService.registerUser(userModel);

        if (response.getData() != null){
            EntityModel<UserModel> userResource = EntityModel.of(((UserModel) response.getData()),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getUserByEmail(((UserModel) response.getData()).getEmail())).withSelfRel());

            response.setData(userResource);
        }

        return response;
    }

    @GetMapping(value = "/getUserByEmail{email}")
    public ResponseModel getUserByEmail(@RequestParam(value = "email") String email) {

        log.info("GET /users/getUserByEmail{email}");
        log.info("Obteniendo usuario por correo");

        ResponseModel response = userService.getUserByEmail(email);
        
        if (response.getData() != null){
            EntityModel<UserModel> userResource = EntityModel.of(((UserModel) response.getData()),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getUserByEmail(((UserModel) response.getData()).getEmail())).withSelfRel());
            response.setData(userResource);
        }
        return response;
    }

    @DeleteMapping(value = "/deleteUserById{id}")
    public ResponseModel deleteUserById(@RequestParam(value = "id") Long id) {

        log.info("GET /users/deleteUserById{id}");
        log.info("Eliminando usuario por id");

        return userService.deleteUser(id);
    }

    @PutMapping(value = "/updateUser{id}")
    public ResponseModel updateUser(@RequestParam(value = "id") Long id, @RequestBody UserModel userModel) {
        log.info("PUT /users/updateUser{id}");
        log.info("Actualizando usuario por id");

        ResponseModel resp = userService.updateUser(id, userModel);

        if (resp.getData() != null) {
            EntityModel<UserModel> userResource = EntityModel.of(((UserModel) resp.getData()),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getUserByEmail(((UserModel) resp.getData()).getEmail())).withSelfRel());
            resp.setData(userResource);
        }
        return resp;
    }

    @PostMapping(value = "/logIn")
    public ResponseModel logIn(@RequestBody LogInModel logIntent) {
        log.info("POST /users/logIn");
        log.info("Validando usuario y contraseña");

        ResponseModel resp = userService.loginUser(logIntent);

        if (resp.getData() != null) {
            EntityModel<UserModel> userResource = EntityModel.of(((UserModel) resp.getData()),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getUserByEmail(((UserModel) resp.getData()).getEmail())).withSelfRel());
            resp.setData(userResource);
        }
        return resp;
    }
}
