package progetto.finale.org.progetto_finale_specializzazione.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "games")
public class Games {

    public Games(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "This camp cannot be null, blank")
    private String name;

    private Integer score;

    @NotBlank(message = "This camp cannot be null or blank")
    private String publisher;

    @NotBlank(message = "This camp cannot be null or blank")
    private String developer;

    //qua collego ad un gioco uno o piu generi
    @OneToMany(mappedBy = "game")
    private List<Genre> genre = new ArrayList<>();

    //qua collego ad un gioco una o piu immagini
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameImage> images = new ArrayList<>();


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public List<GameImage> getImages() {
        return this.images;
    }

    public void setImages(List<GameImage> images) {
        this.images = images;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
