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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/genres")
public class GenresRestController {

    @Autowired
    private GenresService genresService;

    @Autowired
    private GamesService gamesService;

    @GetMapping
    public Page<Genres> index(Pageable pageable) {
        return genresService.findAllPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genres> show(@PathVariable Integer id) {
        Optional<Genres> attemptGenre = genresService.findById(id);

        if (attemptGenre.isEmpty()) {
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genres>(attemptGenre.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<List<Games>> getGamesByGenre(@PathVariable Integer id) {
        Optional<Genres> optionalGenre = genresService.findById(id);
        if (optionalGenre.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Genres genre = optionalGenre.get();

        // Recupera i giochi di questo genere ordinati per nome (o qualsiasi criterio)
        List<Games> games = gamesService.findTop8GamesByGenre(genre);

        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<Genres> store(@RequestBody Genres genre) {

        return new ResponseEntity<Genres>(genresService.create(genre), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genres> update(@RequestBody Genres genre, @PathVariable Integer id) {
        Optional<Genres> attemptGenre = genresService.findById(id);

        if (attemptGenre.isEmpty()) {
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genres>(genresService.update(genre), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genres> delete(@PathVariable Integer id) {
        Optional<Genres> attemptGenre = genresService.findById(id);

        if (attemptGenre.isEmpty()) {
            return new ResponseEntity<Genres>(HttpStatus.NOT_FOUND);
        }

        genresService.delete(id);
        return new ResponseEntity<Genres>(HttpStatus.OK);
    }
}
