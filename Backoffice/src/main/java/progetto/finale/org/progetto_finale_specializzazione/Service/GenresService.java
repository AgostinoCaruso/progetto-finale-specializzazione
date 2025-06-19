package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;
import progetto.finale.org.progetto_finale_specializzazione.Repository.GenresRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;

@Service
public class GenresService {

    @Autowired
    private GenresRepository genresRepository;

    public List<Genres> findAll() {

        return genresRepository.findAll();
    }



    public Page<Genres> findAllPage(Pageable pageable) {

        return genresRepository.findAll(pageable);
    }

    public Optional<Genres> findById(Integer id) {
        return genresRepository.findById(id);
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
