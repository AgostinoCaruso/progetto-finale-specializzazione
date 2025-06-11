package progetto.finale.org.progetto_finale_specializzazione.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;
import progetto.finale.org.progetto_finale_specializzazione.Service.GenresService;

@RestController
@RequestMapping("/api/genres")
public class GenresRestController {
    
    @Autowired
    private GenresService genresService;

    @GetMapping
    public List<Genres> index(){
        return genresService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genres> show(@PathVariable Integer id){
        Optional<Genres> attemptGenre = genresService.findById(id);

        if(attemptGenre.isEmpty()){
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genres>(attemptGenre.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genres> store(@RequestBody Genres genre){

        return new ResponseEntity<Genres>(genresService.create(genre), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genres> update(@RequestBody Genres genre, @PathVariable Integer id){
        Optional<Genres> attemptGenre = genresService.findById(id);

        if(attemptGenre.isEmpty()){
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genres>(genresService.update(genre), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genres> delete(@PathVariable Integer id){
        Optional<Genres> attemptGenre = genresService.findById(id);
        
        if(attemptGenre.isEmpty()){
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }

        genresService.delete(id);
        return new ResponseEntity<Genres>(HttpStatus.OK);
    }
}
