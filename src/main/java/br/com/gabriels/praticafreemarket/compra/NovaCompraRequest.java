package br.com.gabriels.praticafreemarket.compra;

import br.com.gabriels.praticafreemarket.produto.Produto;
import br.com.gabriels.praticafreemarket.usuario.Usuario;

import javax.validation.constraints.*;

public class NovaCompraRequest {

    @Min(1)
    private int quantidade;

    @NotNull
    private Long produtoId;

    @NotNull
    private GatewayPagamento gatewayPagamento;


    public NovaCompraRequest(Long produtoId, int quantidade, GatewayPagamento gatewayPagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Compra toModel(Usuario comprador, Produto produto) {
        return new Compra(comprador, quantidade, produto, gatewayPagamento);
    }
}
