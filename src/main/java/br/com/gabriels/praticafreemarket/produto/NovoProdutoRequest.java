package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.categoria.Categoria;
import br.com.gabriels.praticafreemarket.produto.caracteristica.*;
import br.com.gabriels.praticafreemarket.produto.foto.*;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.jsonwebtoken.lang.Assert.notNull;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;

    @Min(1)
    private Integer quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @Size(min = 1)
    private Set<String> fotos = new HashSet<>();

    @Size(min = 1)
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    private Long categoriaId;

    public NovoProdutoRequest(String nome, BigDecimal preco, Integer quantidadeDisponivel, String descricao,
                              Set<String> fotos, List<NovaCaracteristicaRequest> caracteristicas, long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.fotos = fotos;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
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

    public Set<String> getFotos() {
        return fotos;
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Produto toModel(Usuario usuario, FotoUploader uploader, Function<Long, Categoria> findById) {
        notNull(uploader, "FotoUploader não pode estar nulo!");
        notNull(usuario, "Usuario não pode estar nulo!");
        notNull(findById, "Function não pode ser nula!");

        Categoria categoria = findById.apply(this.categoriaId);
        Set<Foto> uploadedFotos = uploader.upload(fotos, new InformacoesBasicasParaUpload(usuario.getLogin(), this.nome));
        Set<Caracteristica> novasCaracteristicas = this.caracteristicas.stream()
                .map(NovaCaracteristicaRequest::toModel).collect(Collectors.toSet());

        return new Produto(this.nome, this.preco, this.quantidadeDisponivel, this.descricao,
                uploadedFotos, novasCaracteristicas, categoria, usuario);
    }
}
