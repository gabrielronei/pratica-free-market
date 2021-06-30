package br.com.gabriels.praticafreemarket.produto.pergunta;

import br.com.gabriels.praticafreemarket.produto.Produto;
import br.com.gabriels.praticafreemarket.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

import static org.springframework.util.Assert.*;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private LocalDateTime dataEHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "perguntador_id")
    private Usuario perguntador;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, String descricao, Produto produto, Usuario perguntador) {
        hasText(titulo, "O titulo é obrigatorio!");
        hasText(descricao, "A descricao é obrigatoria!");
        notNull(produto, "O produto é obrigatorio!");
        notNull(perguntador, "O usuario perguntador é obrigatorio!");

        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEHoraCriacao = LocalDateTime.now();
        this.produto = produto;
        this.perguntador = perguntador;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailFornecedor() {
        return this.produto.getEmailFornecedor();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }

    public String getDescricao() {
        return descricao;
    }
}
