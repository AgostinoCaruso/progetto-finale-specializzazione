package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;


public interface GenresRepository extends JpaRepository<Genres, Integer>{

        public List<Genres> findByNameContainingIgnoreCase (String search);
        public List<Genres> findAllByOrderByNameAsc();
}