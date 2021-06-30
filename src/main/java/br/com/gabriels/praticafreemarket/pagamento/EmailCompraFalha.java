package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.PagamentoEfetuado;
import br.com.gabriels.praticafreemarket.infra.email.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class EmailCompraFalha {

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    public void executa(PagamentoEfetuado pagamentoEfetuado) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        DadosEmail novoDadosEmail = DadosEmail.deFreeMarketMail()
                .para(pagamentoEfetuado.getEmailComprador())
                .comAssunto(("Falha na compra: " + pagamentoEfetuado.getNomeProduto()))
                .comCorpo("Olá, não foi possivel efetuar sua compra," +
                        " tente novamente em <a href=\"" + pagamentoEfetuado.getURLCompra(uriComponentsBuilder) + "\">  ")
                .build();

        enviadorDeEmail.envia(novoDadosEmail);
    }
}
