package Lv4.repository;

import Lv4.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT entity FROM User entity WHERE entity.name = :name AND entity.password = :password")
    Optional<User> findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
