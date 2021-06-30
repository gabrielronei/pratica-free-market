package br.com.gabriels.praticafreemarket.externo.notaFiscal;

import javax.validation.constraints.NotNull;

public class NovaNotaFiscalRequest {

    @NotNull
    private Long compraId;
    @NotNull
    private Long compradorId;

    public NovaNotaFiscalRequest(Long compraId, Long compradorId) {
        this.compraId = compraId;
        this.compradorId = compradorId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    @Override
    public String toString() {
        return "NovaNotaFiscalRequest{" +
                "compraId=" + compraId +
                ", compradorId=" + compradorId +
                '}';
    }
}
