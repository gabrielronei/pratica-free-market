package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.foto.Foto;
import br.com.gabriels.praticafreemarket.produto.pergunta.Pergunta;

import java.math.BigDecimal;
import java.util.List;

class ProdutoDetalhes {

    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Integer quantidadeDisponivel;
    private final List<String> fotosURL;
    private final double somaAvaliacoes;
    private final int totalAvaliacoes;
    private final double mediaAvaliacoes;
    private final List<CaracteristicasPrincipaisDetalhes> caracteristicasPrincipais;
    private final List<AvaliacoesDetalhes> avaliacoesDetalhes;
    private final List<PerguntaDetalhes> perguntas;

    public ProdutoDetalhes(Produto produto, List<Pergunta> perguntas) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.fotosURL = produto.getFotos().stream().map(Foto::getUrl).toList();
        this.somaAvaliacoes = produto.getSomaAvaliacoes();
        this.totalAvaliacoes = produto.getQuantidadeAvaliacoes();
        this.mediaAvaliacoes = produto.getMediaAvaliacoes();
        this.caracteristicasPrincipais = produto.getCaracteristicas().stream()
                .map(CaracteristicasPrincipaisDetalhes::new).toList();
        this.avaliacoesDetalhes = produto.getAvaliacoes().stream().map(AvaliacoesDetalhes::new).toList();
        this.perguntas = perguntas.stream().map(PerguntaDetalhes::new).toList();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public List<String> getFotosURL() {
        return fotosURL;
    }

    public double getSomaAvaliacoes() {
        return somaAvaliacoes;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public List<CaracteristicasPrincipaisDetalhes> getCaracteristicasPrincipais() {
        return caracteristicasPrincipais;
    }

    public List<AvaliacoesDetalhes> getAvaliacoesDetalhes() {
        return avaliacoesDetalhes;
    }

    public List<PerguntaDetalhes> getPerguntas() {
        return perguntas;
    }
}
