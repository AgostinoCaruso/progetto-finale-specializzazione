package progetto.finale.org.progetto_finale_specializzazione.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class Games {

    public Games() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "This camp cannot be null, blank")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be higher than zero")
    @NotNull(message = "This camp cannot be null")
    private BigDecimal price;

    @Min(1)
    @Max(10)
    @NotNull(message = "This camp cannot be null or blank")
    private Double score;

    @NotBlank(message = "This camp cannot be null or blank")
    private String publisher;

    @NotBlank(message = "This camp cannot be null or blank")
    private String developer;

    @Lob
    @Column(length = 500)
    @NotBlank(message = "This camp cannot be null or blank")
    private String description;

    // qua collego ad un gioco uno o piu generi
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "game_genre", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genres> genres = new ArrayList<>();

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "game_platform", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private List<Platforms> platforms = new ArrayList<>();

    // qua collego ad un gioco una o piu immagini
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Images> images = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public List<Genres> getGenres() {
        return this.genres;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
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

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Images> getImages() {
        return this.images;
    }

    public void setImages(List<Images> images) {
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

    public List<Platforms> getPlatforms() {
        return this.platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }

}
