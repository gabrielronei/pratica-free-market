package br.com.gabriels.praticafreemarket.produto.foto;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    @NotBlank
    @Column(unique = true)
    private String url;

    @Deprecated
    public Foto() {
    }

    public Foto(String url) {
        hasText(url, "A url não pode ser nula!");

        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foto foto = (Foto) o;
        return Objects.equals(url, foto.url);
    }

    @Override
    public int hashCode() {
        return 31 + url.hashCode();
    }
}
