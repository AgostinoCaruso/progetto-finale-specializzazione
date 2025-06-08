package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;
import progetto.finale.org.progetto_finale_specializzazione.Model.Platforms;
import progetto.finale.org.progetto_finale_specializzazione.Repository.GenresRepository;

@Service
public class GenresService {

    @Autowired
    private GenresRepository genresRepository;

    public List<Genres> findAll() {

        return genresRepository.findAll();
    }

    public List<Genres> findAllByNameAsc() {
        return genresRepository.findAllByOrderByNameAsc();
    }

    public List<Genres> findByName(String search) {
        return genresRepository.findByNameContainingIgnoreCase(search);
    }

    public Genres getById(Integer id) {

        return genresRepository.findById(id).get();
    }

    public Genres create(Genres game) {

        return genresRepository.save(game);
    }

    public Genres update(Genres game) {

        return genresRepository.save(game);
    }

    public void delete(Integer id) {
        genresRepository.deleteById(id);
    }
}
