package br.com.gabriels.praticafreemarket.produto.avaliacao;

import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotBlank;

import static io.jsonwebtoken.lang.Assert.*;

public class NovaAvaliacaoRequest {

    @Range(min = 1, max = 5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaAvaliacaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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

    public Avaliacao toModel(Usuario usuario) {
        notNull(usuario, "O usuario não pode estar nulo!");

        return new Avaliacao(this.nota, this.titulo, this.descricao, usuario);
    }
}
