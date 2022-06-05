package ro.ubb.countapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.countapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}
