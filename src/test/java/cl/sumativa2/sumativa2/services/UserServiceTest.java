package cl.sumativa2.sumativa2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import cl.sumativa2.sumativa2.models.ResponseModel;
import cl.sumativa2.sumativa2.models.UserModel;
import cl.sumativa2.sumativa2.repository.UserRepository;
import cl.sumativa2.sumativa2.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    public void registerUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUserName("Navia");
        userModel.setPassword("123456");
        userModel.setEmail("navia@gmail.com");
        userModel.setRol("Cliente");
        userModel.setDispatchAddress1("direccion 1");

        when(userRepositoryMock.save(any(UserModel.class))).thenReturn(userModel);

        ResponseModel result = userService.registerUser(userModel);

        assertEquals("Navia", ((UserModel) result.getData()).getUserName());
        assertEquals("123456", ((UserModel) result.getData()).getPassword());
        assertEquals("navia@gmail.com", ((UserModel) result.getData()).getEmail());
        assertEquals("Cliente", ((UserModel) result.getData()).getRol());
        assertEquals("direccion 1", ((UserModel) result.getData()).getDispatchAddress1());
    }

    @Test
    public void updateUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUserName("Navia");
        userModel.setPassword("123456");
        userModel.setEmail("navia@gmail.com");
        userModel.setRol("Cliente");
        userModel.setDispatchAddress1("direccion 1");

        userRepositoryMock.save(userModel);

        Optional<UserModel> deletedUser = userRepositoryMock.findById(1L);

        assertEquals(Optional.empty(), deletedUser);
    }
}
