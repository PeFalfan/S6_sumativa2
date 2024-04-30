package cl.sumativa2.sumativa2.repository;

import cl.sumativa2.sumativa2.models.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUserTest() {
        UserModel user = new UserModel();
        user.setUserName("Navia");
        user.setPassword("123456");
        user.setEmail("navia@gmail.com");
        user.setRol("Cliente");
        user.setDispatchAddress1("direccion 1");

        UserModel savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("Navia", savedUser.getUserName());

    }

    @Test
    public void deleteUserTest() {
        UserModel user = new UserModel();
        user.setUserName("Navia");
        user.setPassword("123456");
        user.setEmail("navia@gmail.com");
        user.setRol("Cliente");
        user.setDispatchAddress1("direccion 1");

        UserModel savedUser = userRepository.save(user);

        userRepository.delete(user);

        Optional<UserModel> deletedUser = userRepository.findById(1L);

        assertEquals(Optional.empty(), deletedUser);
    }


}
