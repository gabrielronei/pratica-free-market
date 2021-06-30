package br.com.gabriels.praticafreemarket.produto.caracteristica.descricaoCaracteristica;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;

@Entity
public class DescricaoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @Deprecated
    public DescricaoCaracteristica() {
    }

    public DescricaoCaracteristica(String titulo, String descricao) {
        hasText(titulo, "O titulo não pode ser nulo!");
        hasText(descricao, "A descrição não pode ser nula!");

        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescricaoCaracteristica that = (DescricaoCaracteristica) o;
        return Objects.equals(titulo, that.titulo);
    }

    @Override
    public int hashCode() {
        return 31 + titulo.hashCode();
    }
}
