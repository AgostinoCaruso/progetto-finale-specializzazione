package progetto.finale.org.progetto_finale_specializzazione.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="platforms")
public class Platform {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "This camp cannot be null or blank")
    private String name;

    @NotBlank(message = "This camp cannot be null or blank")
    public String logoUrl;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Games getGame() {
        return this.game;
    }

    public void setGame(Games game) {
        this.game = game;
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

    public String getImage() {
        return this.logoUrl;
    }

    public void setImage(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
