package cl.sumativa2.sumativa2.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import cl.sumativa2.sumativa2.models.ResponseModel;
import cl.sumativa2.sumativa2.models.UserModel;
import cl.sumativa2.sumativa2.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceMock;

    @Test
    public void getAllUsersTest() throws Exception {
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setEmail("john@doe.com");
        user1.setUserName("John");
        user1.setPassword("1234");
        user1.setRol("cliente");
        user1.setDispatchAddress1("direccion 1");

        UserModel user2 = new UserModel();
        user2.setId(2L);
        user2.setEmail("jane@doe.com");
        user2.setUserName("jane");
        user2.setPassword("1234");
        user2.setRol("cliente");
        user2.setDispatchAddress1("direccion 1");

        List<UserModel> users = List.of(user1, user2);

        ResponseModel mockResponse = new ResponseModel();
        mockResponse.setData(users);

        when(userServiceMock.getAllUsers()).thenReturn(mockResponse);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data.content[0].id").value(1))
                .andExpect(jsonPath("$.data.content[0].email").value("john@doe.com"))
                .andExpect(jsonPath("$.data.content[0].userName").value("John"))
                .andExpect(jsonPath("$.data.content[0].password").value("1234"))
                .andExpect(jsonPath("$.data.content[0].rol").value("cliente"))
                .andExpect(jsonPath("$.data.content[0].dispatchAddress1").value("direccion 1"));
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        UserModel user1 = new UserModel();
        user1.setEmail("john@doe.com");
        user1.setUserName("John");
        user1.setPassword("1234");
        user1.setRol("cliente");
        user1.setDispatchAddress1("direccion 1");

        userServiceMock.registerUser(user1);

        ResponseModel response = new ResponseModel();
        response.setData(user1);

        when(userServiceMock.getUserByEmail("john@doe.com")).thenReturn(response);

        mockMvc.perform(get("/users/getUserByEmail?email=john@doe.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userName").value("John"))
                .andExpect(jsonPath("$.data.email").value("john@doe.com"))
                .andExpect(jsonPath("$.data.dispatchAddress1").value("direccion 1"));
    }
}
