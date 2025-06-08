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

import jakarta.validation.Valid;
import progetto.finale.org.progetto_finale_specializzazione.Model.Genres;
import progetto.finale.org.progetto_finale_specializzazione.Service.GenresService;

@Controller
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @GetMapping
    public String index(@RequestParam(name = "search", required = false) String search, Model model,
            Authentication authentication) {

        List<Genres> genres;

        if (search != null) {
            genres = genresService.findByName(search);
        } else {
            genres = genresService.findAllByNameAsc();
        }
        model.addAttribute("search", search);
        model.addAttribute("genres", genres);

        return ("genres/index");
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Genres genre = genresService.getById(id);
        model.addAttribute("genre", genre);

        return "genres/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Genres genre = new Genres();
        model.addAttribute("genre", genre);
        model.addAttribute("edit", false);
        return "genres/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genres formGenre, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            return "genres/create-edit";
        }

        genresService.create(formGenre);
        redirectAttributes.addFlashAttribute("successMessage", "You succesfully created a new genre!");
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("genre", genresService.getById(id));
        model.addAttribute("edit", true);

        return "genres/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genre") Genres genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "genres/create-edit";
        }

        genresService.update(genre);

        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        genresService.delete(id);
        return "redirect:/genres";
    }
}
