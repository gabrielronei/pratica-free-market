package br.com.gabriels.praticafreemarket.categoria;

import br.com.gabriels.praticafreemarket.infra.annotation.uniqueField.UniqueField;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.function.Function;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueField(nomeCampo = "nome", classeDominio = Categoria.class, message = "Já existe uma categoria com este nome!")
    private final String nome;

    private final Long idCategoriaMae;

    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Optional<Long> getIdCategoriaMae() {
        return Optional.ofNullable(this.idCategoriaMae);
    }

    public Categoria toModel(Function<Long, Categoria> buscaPeloId) {
        Categoria categoria = new Categoria(this.nome);

        getIdCategoriaMae().ifPresent(idMae -> {
            Categoria categoriaMae = buscaPeloId.apply(idMae);
            categoria.adiciona(categoriaMae);
        });

        return categoria;
    }
}
