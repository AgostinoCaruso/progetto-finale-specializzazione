package progetto.finale.org.progetto_finale_specializzazione.Controller;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

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
import progetto.finale.org.progetto_finale_specializzazione.Model.Games;
import progetto.finale.org.progetto_finale_specializzazione.Model.Images;
import progetto.finale.org.progetto_finale_specializzazione.Service.GamesService;
import progetto.finale.org.progetto_finale_specializzazione.Service.GenresService;
import progetto.finale.org.progetto_finale_specializzazione.Service.PlatformsService;
import progetto.finale.org.progetto_finale_specializzazione.Service.ImagesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private PlatformsService platformsService;

    @Autowired
    private GenresService genresService;

    @Autowired
    private ImagesService imagesService;

    @GetMapping
    public String index(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model,
            Authentication authentication) {

        int pageSize = 8;
        Page<Games> gamesPage;

        if (search != null && !search.isEmpty()) {
            gamesPage = gamesService.findByName(search, PageRequest.of(page, pageSize));
        } else {
            gamesPage = gamesService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("search", search);
        model.addAttribute("gamesPage", gamesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("type", "games");

        return "games/index";
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
    public String store(@Valid @ModelAttribute("game") Games formGame,
            BindingResult bindingResult,
            @RequestParam("imageFiles") MultipartFile[] imageFiles,
            Model model,
            RedirectAttributes redirectAttributes) {
        System.out.println("Numero di file ricevuti: " + imageFiles.length);

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformsService.findAll());
            model.addAttribute("genres", genresService.findAll());
            model.addAttribute("edit", false);
            return "games/create-edit";
        }

        // Percorso di upload
        String uploadDir = "D:/Boolean/uploads/";

        // Cicla su tutti i file ricevuti
        for (MultipartFile imageFile : imageFiles) {
            if (!imageFile.isEmpty()) {
                try {
                    String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                    Path filepath = Paths.get(uploadDir, filename);
                    Files.createDirectories(filepath.getParent());
                    Files.write(filepath, imageFile.getBytes());

                    Images image = new Images();
                    image.setImagePath(filename);
                    image.setGame(formGame); // Associa immagine al gioco
                    System.out.println("Immagine salvata: " + filename);
                    System.out.println("Game associato: " + formGame.getName());

                    formGame.getImages().add(image);
                } catch (IOException e) {
                    e.printStackTrace(); // puoi anche loggare o gestire meglio l'errore
                }
            }
        }

        gamesService.create(formGame);
        redirectAttributes.addFlashAttribute("successMessage", "Gioco creato con successo!");

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
    public String update(@PathVariable Integer id,
            @Valid @ModelAttribute("game") Games gameFromForm,
            BindingResult bindingResult,
            @RequestParam(value = "imageFiles", required = false) MultipartFile[] imageFiles,
            @RequestParam(value = "deleteImageIds", required = false) List<Integer> deleteImageIds,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformsService.findAll());
            model.addAttribute("genres", genresService.findAll());
            model.addAttribute("edit", true);
            return "games/create-edit";
        }

        // Carica il gioco dal DB, comprese le immagini attuali
        Games gameInDb = gamesService.getById(id);

        // Aggiorna i campi del gioco dal form (tranne le immagini)
        gameInDb.setName(gameFromForm.getName());
        gameInDb.setPrice(gameFromForm.getPrice());
        gameInDb.setPublisher(gameFromForm.getPublisher());
        gameInDb.setDeveloper(gameFromForm.getDeveloper());
        gameInDb.setDescription(gameFromForm.getDescription());
        gameInDb.setScore(gameFromForm.getScore());
        gameInDb.setGenres(gameFromForm.getGenres());
        gameInDb.setPlatforms(gameFromForm.getPlatforms());

        // Rimuovi immagini dal gioco e cancella file + record DB
        if (deleteImageIds != null && !deleteImageIds.isEmpty()) {

            // 1) Prima cancella fisicamente e dal DB le immagini da eliminare
            for (Integer imgId : deleteImageIds) {
                imagesService.findById(imgId).ifPresent(imgToDelete -> {
                    Path fileToDelete = Paths.get("D:/Boolean/uploads/", imgToDelete.getImagePath());
                    try {
                        System.out.println("Trying to delete file: " + fileToDelete.toAbsolutePath());
                        boolean deleted = Files.deleteIfExists(fileToDelete);
                        System.out.println("Deleted? " + deleted);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error deleting file: " + fileToDelete.toAbsolutePath());
                    }
                    imagesService.delete(imgToDelete);
                });
            }

            // 2) Poi rimuovi la relazione con le immagini dal gioco
            gameInDb.getImages().removeIf(img -> deleteImageIds.contains(img.getId()));

            // 3) Aggiorna il gioco per sincronizzare la relazione
            gamesService.update(gameInDb);
        }

        // Salva le nuove immagini caricate
        if (imageFiles != null) {
            String uploadDir = "D:/Boolean/uploads/";
            for (MultipartFile imageFile : imageFiles) {
                if (!imageFile.isEmpty()) {
                    try {
                        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                        Path filepath = Paths.get(uploadDir, filename);
                        Files.createDirectories(filepath.getParent());
                        Files.write(filepath, imageFile.getBytes());

                        Images image = new Images();
                        image.setImagePath(filename);
                        image.setGame(gameInDb);
                        gameInDb.getImages().add(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Salva gioco con nuove immagini
        gamesService.update(gameInDb);

        redirectAttributes.addFlashAttribute("successMessage",
                String.format("You successfully updated the game: %s!", gameInDb.getName()));

        return "redirect:/games";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // Recupera il gioco con immagini
        Games game = gamesService.getById(id);

        // Cancella i file fisici delle immagini
        for (Images img : game.getImages()) {
            Path fileToDelete = Paths.get("D:/Boolean/uploads/", img.getImagePath());
            try {
                Files.deleteIfExists(fileToDelete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Elimina il gioco dal DB (assumendo che immagini siano cascade remove)
        gamesService.delete(id);

        redirectAttributes.addFlashAttribute("successMessage",
                String.format("You successfully deleted the game: %s!", game.getName()));

        return "redirect:/games";
    }

}