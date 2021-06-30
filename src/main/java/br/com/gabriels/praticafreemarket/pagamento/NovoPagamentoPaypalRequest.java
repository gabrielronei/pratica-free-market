package br.com.gabriels.praticafreemarket.pagamento;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class NovoPagamentoPaypalRequest {

    @NotBlank
    private String paypalId;

    @Range(min = 0, max = 1)
    private int pagamentoStatusCode;

    public NovoPagamentoPaypalRequest(String paypalId, int pagamentoStatusCode) {
        this.paypalId = paypalId;
        this.pagamentoStatusCode = pagamentoStatusCode;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public int getPagamentoStatusCode() {
        return pagamentoStatusCode;
    }

    public Pagamento toModel() {
        return new Pagamento(this.paypalId, PagamentoStatus.getStatusBy(this.pagamentoStatusCode));
    }
}
