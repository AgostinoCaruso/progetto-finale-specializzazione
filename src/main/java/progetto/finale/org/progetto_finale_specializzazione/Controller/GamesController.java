package progetto.finale.org.progetto_finale_specializzazione.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @GetMapping
    public String index(Model model, Authentication authentication) {

        List<Games> games;
        games = gamesService.findAll();

        model.addAttribute("games", games);
        return ("games/index");
    }
}
