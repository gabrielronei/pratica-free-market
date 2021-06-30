package br.com.gabriels.praticafreemarket.compra;

import br.com.gabriels.praticafreemarket.infra.email.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class EnviadorDeInteresseDeCompraService {

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    public void envia(Compra compra) {

        DadosEmail novoEmailDeCompra = DadosEmail.deFreeMarketMail()
                .para(compra.getEmailFornecedor())
                .comAssunto("Tem alguem interessado em seu " + compra.getNomeProduto())
                .comCorpo("Olá, parece que tem alguem com interesse em comprar seu " + compra.getNomeProduto() +
                        " em breve atualizaremos com mais informações sobre a tentativa de compra!")
                .build();

        enviadorDeEmail.envia(novoEmailDeCompra);
    }
}
