package cl.sumativa2.sumativa2.controllers;

import cl.sumativa2.sumativa2.models.ResponseModel;
import cl.sumativa2.sumativa2.models.User;
import cl.sumativa2.sumativa2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseModel getAllUsers() {

        try {
            ResponseModel response = new ResponseModel();

            List<User> users = userService.getAllUsers();

            if (users == null || users.isEmpty()) {
                response.setMessageResponse("No se encontraron usuarios registrados");
                response.setData(null);
                response.setError(null);

                return response;
            }

            response.setMessageResponse("El se encuentran: " + users.size() + " usuarios registrados");
            response.setData(users);
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
}
