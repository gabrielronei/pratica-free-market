package br.com.gabriels.praticafreemarket.externo.ranking;

import javax.validation.constraints.NotNull;

public class NovoRankingRequest {

    @NotNull
    private Long compraId;
    @NotNull
    private Long fornecedorId;

    public NovoRankingRequest(Long compraId, Long compradorId) {
        this.compraId = compraId;
        this.fornecedorId = compradorId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    @Override
    public String toString() {
        return "NovoRankingRequest{" +
                "compraId=" + compraId +
                ", fornecedorId=" + fornecedorId +
                '}';
    }
}
