package br.com.gabriels.praticafreemarket.produto.pergunta;

import br.com.gabriels.praticafreemarket.infra.email.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class EnviadorDePerguntaEmailService {

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    public void envia(Pergunta pergunta, UriComponentsBuilder uriBuilder) {
        String perguntaUrl = uriBuilder.path("/produto/{id}")
                .buildAndExpand(pergunta.getId())
                .toString();

        DadosEmail novoDadosEmail = DadosEmail.deFreeMarketMail()
                .para(pergunta.getEmailFornecedor())
                .comAssunto("Você tem uma nova pergunta sobre o " + pergunta.getNomeProduto())
                .comCorpo("Olá, tem uma nova pergunta sobre " + pergunta.getTitulo() + " para ver mais detalhes clique em: <a href=\'" + perguntaUrl + "\'/>!")
                .build();

        enviadorDeEmail.envia(novoDadosEmail);
    }
}
