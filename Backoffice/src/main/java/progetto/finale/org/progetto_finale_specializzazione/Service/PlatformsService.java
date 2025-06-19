package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Platforms;
import progetto.finale.org.progetto_finale_specializzazione.Repository.PlatformsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class PlatformsService {

    @Autowired
    private PlatformsRepository platformsRepository;

    public List<Platforms> findAll() {

        return platformsRepository.findAll();
    }

    public Page<Platforms> findAllPage(Pageable pageable) {

        return platformsRepository.findAll(pageable);
    }

    public Optional<Platforms> findById(Integer id) {
        return platformsRepository.findById(id);
    }

    public List<Platforms> findAllByNameAsc() {
        return platformsRepository.findAllByOrderByNameAsc();
    }

    public List<Platforms> findByName(String search) {
        return platformsRepository.findByNameContainingIgnoreCase(search);
    }

    public Platforms getById(Integer id) {

        return platformsRepository.findById(id).get();
    }

    public Platforms create(Platforms game) {

        return platformsRepository.save(game);
    }

    public Platforms update(Platforms game) {

        return platformsRepository.save(game);
    }

    public void delete(Integer id) {
        platformsRepository.deleteById(id);
    }
}
