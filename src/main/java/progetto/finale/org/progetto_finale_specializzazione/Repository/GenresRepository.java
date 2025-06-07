package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genre;


public interface GenresRepository extends JpaRepository<Genre, Integer>{

        public List<Genre> findByNameContainingIgnoreCase (String search);
}