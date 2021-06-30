package br.com.gabriels.praticafreemarket.compra;

import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

import static io.jsonwebtoken.lang.Assert.notNull;

public class PagamentoEfetuado {

    private final Long compraId;
    private final Long compradorId;
    private final Long fornecedorId;
    private final String emailComprador;
    private final String nomeProduto;
    private final BigDecimal valorTotal;
    private final boolean sucesso;
    private final Compra compra;


    public PagamentoEfetuado(Compra compra) {
        notNull(compra, "Compra não pode estar nula!");

        this.compra = compra;
        this.compraId = compra.getId();
        this.compradorId = compra.getCompradorId();
        this.fornecedorId = compra.getFornecedorId();
        this.emailComprador = compra.getEmailComprador();
        this.nomeProduto = compra.getNomeProduto();
        this.valorTotal = compra.getValorTotal();
        this.sucesso = compra.isPaga();
    }

    public Long getIdCompra() {
        return this.compraId;
    }

    public Long getCompradorId() {
        return this.compradorId;
    }

    public Long getFornecedorId() {
        return this.fornecedorId;
    }

    public String getEmailComprador() {
        return this.emailComprador;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }

    public boolean comSucesso() {
        return this.sucesso;
    }

    public String getURLCompra(UriComponentsBuilder uriComponentsBuilder) {
        return this.compra.getURLCompra(uriComponentsBuilder);
    }
}
