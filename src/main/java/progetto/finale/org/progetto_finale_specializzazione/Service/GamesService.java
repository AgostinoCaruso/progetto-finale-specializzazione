package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Repository.GamesRepository;

@Service
public class GamesService {

    @Autowired
    private GamesRepository gamesRepository;

    public List<Games> findAll() {

        return gamesRepository.findAll();
    }
    public List<Games> findByName(String search){
        return gamesRepository.findByNameContainingIgnoreCase (search);
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
}