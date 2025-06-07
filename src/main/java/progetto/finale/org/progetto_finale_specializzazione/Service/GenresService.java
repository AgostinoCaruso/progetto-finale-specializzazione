package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genre;
import progetto.finale.org.progetto_finale_specializzazione.Repository.GenresRepository;

@Service
public class GenresService {

    @Autowired
    private GenresRepository genresRepository;

    public List<Genre> findAll() {

        return genresRepository.findAll();
    }

    public List<Genre> findByName(String search) {
        return genresRepository.findByNameContainingIgnoreCase(search);
    }

    public Genre getById(Integer id) {

        return genresRepository.findById(id).get();
    }

    public Genre create(Genre game) {

        return genresRepository.save(game);
    }

    public Genre update(Genre game) {

        return genresRepository.save(game);
    }

    public void delete(Integer id) {
        genresRepository.deleteById(id);
    }
}
