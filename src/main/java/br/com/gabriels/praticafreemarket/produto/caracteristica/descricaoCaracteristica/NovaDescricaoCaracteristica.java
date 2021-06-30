package br.com.gabriels.praticafreemarket.produto.caracteristica.descricaoCaracteristica;

import javax.validation.constraints.NotBlank;

public class NovaDescricaoCaracteristica {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    public NovaDescricaoCaracteristica(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public DescricaoCaracteristica toModel() {
        return new DescricaoCaracteristica(this.titulo, this.descricao);
    }
}
