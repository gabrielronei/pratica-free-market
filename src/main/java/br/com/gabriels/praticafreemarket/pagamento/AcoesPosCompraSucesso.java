package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.PagamentoEfetuado;
import org.springframework.stereotype.Service;

@Service
interface AcoesPosCompraSucesso {
    void executa(PagamentoEfetuado pagamentoEfetuado);
}
