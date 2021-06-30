package br.com.gabriels.praticafreemarket.produto.pergunta;

import br.com.gabriels.praticafreemarket.produto.Produto;
import br.com.gabriels.praticafreemarket.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private final String titulo;

    @NotBlank
    private final String descricao;

    public NovaPerguntaRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Pergunta toModel(Produto produto, Usuario perguntador) {
        return new Pergunta(this.titulo, this.descricao, produto, perguntador);
    }
}
