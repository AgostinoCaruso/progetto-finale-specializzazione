package progetto.finale.org.progetto_finale_specializzazione.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;

public interface GamesRepository extends JpaRepository<Games, Integer> {

        public Page<Games> findByNameContainingIgnoreCase(String search, Pageable pageable);

        public List<Games> findByGenresOrderByNameAsc(Genres genres);

        public List<Games> findTop8ByGenresInAndIdNot(List<Genres> genres, Integer id);

        public List<Games> findTop8ByPlatformsIdOrderByScoreDesc(Integer platformId);

        public List<Games> findTop3ByOrderByScoreDesc();

        public List<Games> findTop8ByGenresOrderByScoreDesc(Genres genre);

}