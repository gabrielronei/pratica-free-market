package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.caracteristica.descricaoCaracteristica.DescricaoCaracteristica;

class CaracteristicasDescricaoDetalhes {

    private final String titulo;
    private final String descricao;

    public CaracteristicasDescricaoDetalhes(DescricaoCaracteristica descricaoCaracteristica) {
        this.titulo = descricaoCaracteristica.getTitulo();
        this.descricao = descricaoCaracteristica.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
