package br.com.gabriels.praticafreemarket.compra;

import br.com.gabriels.praticafreemarket.pagamento.Pagamento;
import br.com.gabriels.praticafreemarket.produto.Produto;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

import static br.com.gabriels.praticafreemarket.compra.Status.INICIADO;
import static org.springframework.util.Assert.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    private int quantidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status = INICIADO;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "compra_id")
    private Set<Pagamento> tentativaPagamento = new HashSet<>();

    @NotNull
    @PositiveOrZero
    private BigDecimal valorTotal = BigDecimal.ZERO;

    private boolean paga;

    @Deprecated
    public Compra() {
    }

    public Compra(Usuario comprador, int quantidade, Produto produto, GatewayPagamento gatewayPagamento) {
        notNull(comprador, "O comprador não pode estar nulo!");
        notNull(produto, "O produto não pode estar nulo!");
        notNull(gatewayPagamento, "O gatewayPagamento é obrigatorio!");
        state(quantidade > 0, "A quantidade precisa ser maior que 0!");

        this.comprador = comprador;
        this.quantidade = quantidade;
        this.produto = produto;
        this.gatewayPagamento = gatewayPagamento;
        this.valorTotal = produto.getValorTotal(this.quantidade);
    }

    public Long getId() {
        return id;
    }

    public Long getCompradorId() {
        return this.comprador.getId();
    }

    public Long getFornecedorId() {
        return this.produto.getIdFornecedor();
    }

    public String getEmailComprador() {
        return this.comprador.getLogin();
    }

    public String getEmailFornecedor() {
        return this.produto.getEmailFornecedor();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }

    public String getURLCompra(UriComponentsBuilder uriComponentsBuilder) {
        notNull(uriComponentsBuilder, "O uriComponentsBuilder não pode estar nulo!");

        return this.gatewayPagamento.getURL(this, uriComponentsBuilder);
    }

    public boolean isPaga() {
        return this.paga;
    }

    public Optional<PagamentoEfetuado> adicionarPagamento(Pagamento pagamento) {
        notNull(pagamento, "O pagamento não pode estar nulo!");
        if (this.paga) return Optional.empty();

        this.tentativaPagamento.add(pagamento);

        if (pagamento.estaPago()) {
            this.status = status.finaliza();
            this.paga = true;
        }

        return Optional.of(new PagamentoEfetuado(this));
    }


    public String getStatusName() {
        return this.status.name();
    }

    public String getGatewayPagamentoName() {
        return this.gatewayPagamento.name();
    }
}
