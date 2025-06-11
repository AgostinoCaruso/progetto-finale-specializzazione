package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import progetto.finale.org.progetto_finale_specializzazione.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
} 
