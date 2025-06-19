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

import progetto.finale.org.progetto_finale_specializzazione.Model.Platforms;
import progetto.finale.org.progetto_finale_specializzazione.Service.PlatformsService;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/platforms")
public class PlatformsRestController {

    @Autowired
    private PlatformsService platformsService;

    @Autowired
    private GamesService gamesService;

    @GetMapping
    public List<Platforms> index() {
        return platformsService.findAll();
    }

    @GetMapping("/pages")
    public Page<Platforms> index(Pageable pageable) {
        return platformsService.findAllPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platforms> show(@PathVariable Integer id) {
        Optional<Platforms> attemptPlatform = platformsService.findById(id);

        if (attemptPlatform.isEmpty()) {
            return new ResponseEntity<Platforms>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Platforms>(attemptPlatform.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<List<Games>> getGamesByPlatform(@PathVariable Integer id) {
        Optional<Platforms> optionalPlatform = platformsService.findById(id);
        if (optionalPlatform.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Platforms platform = optionalPlatform.get();

        // Recupera i giochi di questo genere ordinati per nome (o qualsiasi criterio)
        List<Games> games = gamesService.findTop8GamesByPlatform(platform);

        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<Platforms> store(@RequestBody Platforms platform) {
        return new ResponseEntity<Platforms>(platformsService.create(platform), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Platforms> update(@RequestBody Platforms platform, @PathVariable Integer id) {
        Optional<Platforms> attemptPlatofrm = platformsService.findById(id);

        if (attemptPlatofrm.isEmpty()) {
            return new ResponseEntity<Platforms>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Platforms>(platformsService.update(platform), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Platforms> delete(@PathVariable Integer id) {
        Optional<Platforms> attemptPlatform = platformsService.findById(id);

        if (attemptPlatform.isEmpty()) {
            return new ResponseEntity<Platforms>(HttpStatus.NOT_FOUND);
        }

        platformsService.delete(id);
        return new ResponseEntity<Platforms>(HttpStatus.OK);
    }

}
