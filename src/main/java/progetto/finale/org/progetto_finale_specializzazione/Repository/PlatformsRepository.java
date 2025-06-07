package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progetto.finale.org.progetto_finale_specializzazione.Model.Platform;


public interface PlatformsRepository extends JpaRepository<Platform, Integer>{

        public List<Platform> findByNameContainingIgnoreCase (String search);
}