package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.avaliacao.Avaliacao;

class AvaliacoesDetalhes {

    private final String titulo;
    private final String descricao;
    private final int nota;

    public AvaliacoesDetalhes(Avaliacao avaliacao) {
        this.titulo = avaliacao.getTitulo();
        this.descricao = avaliacao.getDescricao();
        this.nota = avaliacao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}
