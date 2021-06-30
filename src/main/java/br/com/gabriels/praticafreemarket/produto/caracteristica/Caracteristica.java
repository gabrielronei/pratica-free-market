package br.com.gabriels.praticafreemarket.produto.caracteristica;

import br.com.gabriels.praticafreemarket.produto.caracteristica.descricaoCaracteristica.DescricaoCaracteristica;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

import static org.springframework.util.Assert.*;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "caracteristica_id")
    private Set<DescricaoCaracteristica> descricoes = new HashSet<>();

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(String titulo, Set<DescricaoCaracteristica> descricoes) {
        hasText(titulo, "O titulo não pode ser nulo!");
        state(!descricoes.isEmpty(), "A descrição não pode ser nula!");

        this.titulo = titulo;
        this.descricoes = descricoes;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Set<DescricaoCaracteristica> getDescricoes() {
        return descricoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(titulo, that.titulo);
    }

    @Override
    public int hashCode() {
        return 31 + titulo.hashCode();
    }
}
