package progetto.finale.org.progetto_finale_specializzazione.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Model.Images;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;
import progetto.finale.org.progetto_finale_specializzazione.Service.GenresService;
import progetto.finale.org.progetto_finale_specializzazione.Service.PlatformsService;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private PlatformsService platformsService;

    @Autowired
    private GenresService genresService;

    @GetMapping
    public String index(@RequestParam(name = "search", required = false) String search, Model model,
            Authentication authentication) {

        List<Games> games;

        if (search != null) {
            games = gamesService.findByName(search);
        } else {
            games = gamesService.findAll();
        }
        model.addAttribute("search", search);
        model.addAttribute("games", games);
        model.addAttribute("type", "games");
        return ("games/index");
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Games game = gamesService.getById(id);
        model.addAttribute("game", game);

        return "games/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Games game = new Games();
        game.getImages().add(new Images());
        
        model.addAttribute("game", game);
        model.addAttribute("platforms", platformsService.findAll());
        model.addAttribute("genres", genresService.findAll());
        model.addAttribute("edit", false);
        return "games/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("game") Games formGame, BindingResult bindingResult, Model model) {

        // Ricollega ogni immagine al gioco (evita il problema game_id = null)
        for (Images img : formGame.getImages()) {
            img.setGame(formGame);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformsService.findAll());
            model.addAttribute("genres", genresService.findAll());
            model.addAttribute("edit", false);
            return "games/create-edit";
        }

        gamesService.create(formGame);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("game", gamesService.getById(id));
        model.addAttribute("platforms", platformsService.findAll());
        model.addAttribute("genres", genresService.findAll());
        model.addAttribute("edit", true);
        return "games/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("game") Games game, BindingResult bindingResult, Model model) {

        for (Images img : game.getImages()) {
            img.setGame(game);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformsService.findAll());
            model.addAttribute("genres", genresService.findAll());
            model.addAttribute("edit", true);
            return "games/create-edit";
        }

        gamesService.update(game);

        return "redirect:/games";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        gamesService.delete(id);
        return "redirect:/games";
    }
}