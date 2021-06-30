package br.com.gabriels.praticafreemarket.pagamento;

import javax.validation.constraints.*;

public class NovoPagamentoPagseguroRequest {

    @NotBlank
    private String paypalId;

    @NotNull
    private PagamentoStatus pagamentoStatusCode;

    public NovoPagamentoPagseguroRequest(String paypalId, PagamentoStatus pagamentoStatusCode) {
        this.paypalId = paypalId;
        this.pagamentoStatusCode = pagamentoStatusCode;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public PagamentoStatus getPagamentoStatusCode() {
        return pagamentoStatusCode;
    }

    public Pagamento toModel() {
        return new Pagamento(this.paypalId, this.pagamentoStatusCode);
    }
}
