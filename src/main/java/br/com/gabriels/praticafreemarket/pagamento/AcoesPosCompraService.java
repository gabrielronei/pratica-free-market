package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.PagamentoEfetuado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AcoesPosCompraService {

    @Autowired
    private List<AcoesPosCompraSucesso> acoesSucesso;

    @Autowired
    private EmailCompraFalha emailCompraFalha;

    public void executa(PagamentoEfetuado pagamentoEfetuado) {
        if (pagamentoEfetuado.comSucesso()) {
            acoesSucesso.forEach(acao -> acao.executa(pagamentoEfetuado));
        } else {
            emailCompraFalha.executa(pagamentoEfetuado);
        }
    }
}
