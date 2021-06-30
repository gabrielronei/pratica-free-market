package br.com.gabriels.praticafreemarket.produto.foto;

import static org.springframework.util.Assert.hasText;

public record InformacoesBasicasParaUpload(String nomeUsuario, String titulo) {

    public InformacoesBasicasParaUpload {
        hasText(nomeUsuario, "O nome do usuario não pode ser nula!");
        hasText(titulo, "O titulo não pode ser nulo!");
    }

    public String getSlug() {
        return this.titulo.replace(" ", "-");
    }
}
