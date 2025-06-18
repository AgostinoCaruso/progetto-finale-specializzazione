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

import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;
import progetto.finale.org.progetto_finale_specializzazione.Service.GenresService;
import progetto.finale.org.progetto_finale_specializzazione.Service.PlatformsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/games")
public class GamesRestController {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private PlatformsService platformsService;

    @Autowired
    private GenresService genresService;

    @GetMapping
    public Page<Games> index(Pageable pageable) {
        return gamesService.findAll(pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Games>> searchGames(@RequestParam("q") String query, Pageable pageable) {
        Page<Games> results = gamesService.findByName(query, pageable);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> show(@PathVariable Integer id) {
        Optional<Games> attemptGame = gamesService.findById(id);

        if (attemptGame.isEmpty()) {
            return new ResponseEntity<Games>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Games>(attemptGame.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Games> store(@RequestBody Games game) {
        return new ResponseEntity<Games>(gamesService.create(game), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Games> update(@RequestBody Games game, @PathVariable Integer id) {
        Optional<Games> attemptGame = gamesService.findById(id);

        if (attemptGame.isEmpty()) {
            return new ResponseEntity<Games>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Games>(gamesService.update(game), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Games> delete(@PathVariable Integer id) {
        Optional<Games> attemptGame = gamesService.findById(id);

        if (attemptGame.isEmpty()) {
            return new ResponseEntity<Games>(HttpStatus.NOT_FOUND);
        }

        gamesService.delete(id);
        return new ResponseEntity<Games>(HttpStatus.OK);
    }
}
