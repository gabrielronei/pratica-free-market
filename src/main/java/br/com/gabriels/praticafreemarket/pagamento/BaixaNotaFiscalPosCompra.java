package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.PagamentoEfetuado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
class BaixaNotaFiscalPosCompra implements AcoesPosCompraSucesso {

    @Override
    public void executa(PagamentoEfetuado pagamentoEfetuado) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> novaNotaFiscalRequest = Map.of("compraId", pagamentoEfetuado.getIdCompra(),
                "compradorId", pagamentoEfetuado.getCompradorId());

        restTemplate.postForEntity("http://localhost:8080/gerar/nota-fiscal", novaNotaFiscalRequest, ResponseEntity.class);
    }
}