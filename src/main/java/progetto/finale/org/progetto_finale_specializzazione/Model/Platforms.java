package progetto.finale.org.progetto_finale_specializzazione.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="platforms")
public class Platforms {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "This camp cannot be null or blank")
    private String name;

    @NotBlank(message = "This camp cannot be null or blank")
    public String logoUrl;

    @ManyToMany(mappedBy = "platforms")
    @JsonBackReference
    private List<Games> games;

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Games> getGames() {
        return this.games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }
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
}
