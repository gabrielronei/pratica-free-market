package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.categoria.Categoria;
import br.com.gabriels.praticafreemarket.compra.*;
import br.com.gabriels.praticafreemarket.produto.avaliacao.Avaliacao;
import br.com.gabriels.praticafreemarket.produto.caracteristica.Caracteristica;
import br.com.gabriels.praticafreemarket.produto.foto.Foto;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

import static org.springframework.util.Assert.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;

    @PositiveOrZero
    private Integer quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<Foto> fotos = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Usuario fornecedor;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @Version
    private int versao;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal preco, Integer quantidadeDisponivel, String descricao,
                   Set<Foto> fotos, Set<Caracteristica> caracteristicas, Categoria categoria, Usuario fornecedor) {
        hasText(nome, "O nome é obrigatorio!");
        notNull(preco, "O preço é obrigatorio!");
        notNull(quantidadeDisponivel, "A quantidade disponivel é obrigatoria!");
        hasText(descricao, "A descricao é obrigatoria!");
        state(!fotos.isEmpty(), "Uma foto pelo menos é obrigatoria!");
        state(!caracteristicas.isEmpty(), "Uma caracteristica pelo menos é obrigatoria!");
        state(Objects.nonNull(categoria), "Uma categoria é obrigatoria!");
        state(Objects.nonNull(fornecedor), "O usuario(dono do produto) é obrigatoria!");

        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.fotos = fotos;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdFornecedor() {
        return this.fornecedor.getId();
    }

    public String getEmailFornecedor() {
        return this.fornecedor.getLogin();
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public void adicionarFotos(Set<Foto> novasFotos) {
        notEmpty(novasFotos, "Fotos não podem estar nulas!");

        this.fotos.addAll(novasFotos);
    }

    public void adicionarAvaliacao(Avaliacao novaAvaliacao) {
        notNull(novaAvaliacao, "Opinião não pode estar nula!");

        this.avaliacoes.add(novaAvaliacao);
    }

    public Optional<Compra> novaCompra(NovaCompraRequest novaCompraRequest, Usuario comprador) {
        notNull(comprador, "O comprador é obrigatorio!");
        notNull(novaCompraRequest, "NovaCompraRequest não pode estar nula!");

        int quantidadeSolicitada = novaCompraRequest.getQuantidade();

        if (this.quantidadeDisponivel >= quantidadeSolicitada) {
            this.quantidadeDisponivel -= quantidadeSolicitada;
            return Optional.of(novaCompraRequest.toModel(comprador, this));
        }

        return Optional.empty();
    }

    public int getQuantidadeAvaliacoes() {
        return this.avaliacoes.size();
    }

    public BigDecimal getValorTotal(int quantidadeItens) {
        return this.preco.multiply(new BigDecimal(quantidadeItens));
    }

    public double getSomaAvaliacoes() {
        return this.avaliacoes.stream().mapToInt(Avaliacao::getNota).sum();
    }


    public double getMediaAvaliacoes() {
        return this.avaliacoes.stream().mapToInt(Avaliacao::getNota)
                .average().orElse(0);
    }

    public Set<Caracteristica> getCaracteristicas() {
        return Collections.unmodifiableSet(caracteristicas);
    }

    public List<Avaliacao> getAvaliacoes() {
        return Collections.unmodifiableList(avaliacoes);
    }
}
