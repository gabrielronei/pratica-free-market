package br.com.gabriels.praticafreemarket.produto.caracteristica;

import br.com.gabriels.praticafreemarket.produto.caracteristica.descricaoCaracteristica.NovaDescricaoCaracteristica;

import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.Collectors;

public class NovaCaracteristicaRequest {

    @NotBlank
    private String titulo;

    @Size(min = 3)
    private Set<NovaDescricaoCaracteristica> descricoesCaracteristicas = new HashSet<>();

    public NovaCaracteristicaRequest(String titulo, Set<NovaDescricaoCaracteristica> descricoesCaracteristicas) {
        this.titulo = titulo;
        this.descricoesCaracteristicas = descricoesCaracteristicas;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<NovaDescricaoCaracteristica> getDescricoesCaracteristicas() {
        return descricoesCaracteristicas;
    }

    public Caracteristica toModel() {
        return new Caracteristica(this.titulo, this.descricoesCaracteristicas.stream()
                .map(NovaDescricaoCaracteristica::toModel).collect(Collectors.toSet()));
    }
}
