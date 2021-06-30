package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.caracteristica.Caracteristica;

import java.util.List;

class CaracteristicasPrincipaisDetalhes {

    private final String titulo;
    private final List<CaracteristicasDescricaoDetalhes> caracteristicasDescricaoDetalhes;

    public CaracteristicasPrincipaisDetalhes(Caracteristica caracteristica) {
        this.titulo = caracteristica.getTitulo();
        this.caracteristicasDescricaoDetalhes = caracteristica.getDescricoes().stream().map(CaracteristicasDescricaoDetalhes::new).toList();
    }

    public String getTitulo() {
        return titulo;
    }

    public List<CaracteristicasDescricaoDetalhes> getCaracteristicasDescricaoDetalhes() {
        return caracteristicasDescricaoDetalhes;
    }
}
