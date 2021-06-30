package br.com.gabriels.praticafreemarket.produto.avaliacao;

import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static org.springframework.util.Assert.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 1, max = 5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Deprecated
    public Avaliacao() {
    }

    public Avaliacao(int nota, String titulo, String descricao, Usuario usuario) {
        state((nota >= 1 && nota <= 5), "A nota precisa estar entre 1 e 5!");
        hasText(titulo, "O titulo é obrigatorio!");
        hasText(descricao, "A descricao é obrigatoria!");
        notNull(usuario, "O usuario não pode ser nulo!");

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
