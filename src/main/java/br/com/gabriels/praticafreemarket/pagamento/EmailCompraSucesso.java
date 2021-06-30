package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.PagamentoEfetuado;
import br.com.gabriels.praticafreemarket.infra.email.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class EmailCompraSucesso implements AcoesPosCompraSucesso {

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    @Override
    public void executa(PagamentoEfetuado pagamentoEfetuado) {
        DadosEmail novoDadosEmail = DadosEmail.deFreeMarketMail()
                .para(pagamentoEfetuado.getEmailComprador())
                .comAssunto(("Parabéns! Compra Efetuada: " + pagamentoEfetuado.getNomeProduto()))
                .comCorpo("Olá, seu produto foi comprado com sucesso, pelo valor total de R$ " + pagamentoEfetuado.getValorTotal() +
                        ", deve ser entregue nas proximas semanas! \nAgradecemos sua preferencia, abraços de FreeMarket :)")
                .build();

        enviadorDeEmail.envia(novoDadosEmail);
    }
}
