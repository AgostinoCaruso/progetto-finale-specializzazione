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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;
import jakarta.validation.Valid;
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;

import progetto.finale.org.progetto_finale_specializzazione.Model.Platforms;
import progetto.finale.org.progetto_finale_specializzazione.Service.PlatformsService;

@Controller
@RequestMapping("/platforms")
public class PlatformsController {

    @Autowired
    private PlatformsService platformsService;

    @Autowired
    private GamesService gamesService;

    @GetMapping
    public String index(@RequestParam(name = "search", required = false) String search, Model model,
            Authentication authentication) {

        List<Platforms> platforms;

        if (search != null) {
            platforms = platformsService.findByName(search);
        } else {
            platforms = platformsService.findAllByNameAsc();
        }
        model.addAttribute("search", search);
        model.addAttribute("platforms", platforms);

        return ("platforms/index");
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Platforms platform = platformsService.getById(id);

        List<Games> topGames = gamesService.findTopGamesPlatform(id);

        model.addAttribute("platform", platform);
        model.addAttribute("games", topGames);

        return "platforms/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Platforms platform = new Platforms();
        model.addAttribute("platform", platform);
        model.addAttribute("edit", false);
        return "platforms/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("platform") Platforms platformsForm, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "platforms/create-edit";
        }
        redirectAttributes.addFlashAttribute("successMessage",
                String.format("You succesfully created a new platform: %s !", platformsForm.getName()));
        platformsService.create(platformsForm);
        return "redirect:/platforms";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("platform", platformsService.getById(id));
        model.addAttribute("edit", true);

        return "platforms/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("platform") Platforms platforms, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "platforms/create-edit";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                String.format("You succesfully updated the platform: %s !", platforms.getName()));
        platformsService.update(platforms);

        return "redirect:/platforms";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage",
                String.format("You succesfully deleted the platform: %s !", platformsService.getById(id).getName()));
        platformsService.delete(id);
        return "redirect:/platforms";
    }
}