package br.com.gabriels.praticafreemarket.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static org.springframework.util.Assert.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        hasText(nome, "O nome da categoria é obrigatório!");

        this.nome = nome;
    }

    public void adiciona(Categoria categoriaMae) {
        notNull(categoriaMae, "A categoria mãe não pode ser nula!");
        state(!categoriaMae.equals(this), "A propria categoria não pode ser mãe dela mesma!");

        this.categoriaMae = categoriaMae;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return 31 + nome.hashCode();
    }
}
