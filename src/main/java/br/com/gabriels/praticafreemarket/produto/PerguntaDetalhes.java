package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.pergunta.Pergunta;

class PerguntaDetalhes {

    private final String titulo;
    private final String descricao;

    public PerguntaDetalhes(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.descricao = pergunta.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
