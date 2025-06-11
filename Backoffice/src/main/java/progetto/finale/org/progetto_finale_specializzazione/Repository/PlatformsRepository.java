package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progetto.finale.org.progetto_finale_specializzazione.Model.Platforms;


public interface PlatformsRepository extends JpaRepository<Platforms, Integer>{

        public List<Platforms> findAllByOrderByNameAsc();
        public List<Platforms> findByNameContainingIgnoreCase (String search);
}