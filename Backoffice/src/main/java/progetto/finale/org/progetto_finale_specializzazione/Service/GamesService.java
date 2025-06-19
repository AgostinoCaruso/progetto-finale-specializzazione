package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;
import progetto.finale.org.progetto_finale_specializzazione.Repository.GamesRepository;

@Service
public class GamesService {

    @Autowired
    private GamesRepository gamesRepository;

    public Page<Games> findAll(Pageable pageable) {
        return gamesRepository.findAll(pageable);
    }

    public Page<Games> findByName(String search, Pageable pageable) {
        return gamesRepository.findByNameContainingIgnoreCase(search, pageable);
    }

    public List<Games> findRecomandation(List<Genres> genres, Integer id){
        return gamesRepository.findTop8ByGenresInAndIdNot(genres, id);
    }

    public Optional<Games> findById(Integer id) {
        return gamesRepository.findById(id);
    }

    public Games getById(Integer id) {

        return gamesRepository.findById(id).get();
    }

    public Games create(Games game) {

        return gamesRepository.save(game);
    }

    public Games update(Games game) {

        return gamesRepository.save(game);
    }

    public void delete(Integer id) {
        gamesRepository.deleteById(id);
    }

    public List<Games> findByGenreOrderByNameAsc(Genres genres) {
        return gamesRepository.findByGenresOrderByNameAsc(genres);
    }
}