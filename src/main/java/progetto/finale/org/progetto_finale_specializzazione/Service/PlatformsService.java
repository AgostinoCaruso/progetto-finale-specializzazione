package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Platform;
import progetto.finale.org.progetto_finale_specializzazione.Repository.PlatformsRepository;

@Service
public class PlatformsService {

    @Autowired
    private PlatformsRepository platformsRepository;

    public List<Platform> findAll() {

        return platformsRepository.findAll();
    }

    public List<Platform> findByName(String search) {
        return platformsRepository.findByNameContainingIgnoreCase(search);
    }

    public Platform getById(Integer id) {

        return platformsRepository.findById(id).get();
    }

    public Platform create(Platform game) {

        return platformsRepository.save(game);
    }

    public Platform update(Platform game) {

        return platformsRepository.save(game);
    }

    public void delete(Integer id) {
        platformsRepository.deleteById(id);
    }
}
