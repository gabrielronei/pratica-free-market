package br.com.gabriels.praticafreemarket.compra;

import java.math.BigDecimal;

class CompraDetalhes {

    private final String nomeProduto;
    private final String status;
    private final String gatewayPagamento;
    private final BigDecimal valor;

    public CompraDetalhes(Compra compra) {
        this.nomeProduto = compra.getNomeProduto();
        this.status = compra.getStatusName();
        this.gatewayPagamento = compra.getGatewayPagamentoName();
        this.valor = compra.getValorTotal();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getStatus() {
        return status;
    }

    public String getGatewayPagamento() {
        return gatewayPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
