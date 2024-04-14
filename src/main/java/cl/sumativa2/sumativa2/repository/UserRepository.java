package cl.sumativa2.sumativa2.repository;

import cl.sumativa2.sumativa2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
