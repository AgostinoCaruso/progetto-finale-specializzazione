package progetto.finale.org.progetto_finale_specializzazione.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.Images;
import progetto.finale.org.progetto_finale_specializzazione.Repository.ImagesRepository;

@Service
public class ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

    public Optional<Images> findById(Integer id){
        return imagesRepository.findById(id);
    }

    public void delete(Images image) {
        imagesRepository.delete(image);
    }
}
