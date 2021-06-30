package br.com.gabriels.praticafreemarket.pagamento;

import br.com.gabriels.praticafreemarket.compra.*;
import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PagamentoController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private AcoesPosCompraService acoesPosCompraService;

    @PostMapping("/pagamento-paypal/{compraId}")
    @Transactional
    public ResponseEntity pagar(@PathVariable("compraId") Long compraId, @RequestBody @Valid NovoPagamentoPaypalRequest novoPagamentoPaypal) {
        Compra compra = compraRepository.findById(compraId).orElseThrow(NotFoundException::new);

        Pagamento pagamento = novoPagamentoPaypal.toModel();
        Optional<PagamentoEfetuado> possivelPagamentoEfetuado = compra.adicionarPagamento(pagamento);

        return executa(possivelPagamentoEfetuado);
    }

    @PostMapping("/pagamento-pagseguro/{compraId}")
    @Transactional
    public ResponseEntity pagar(@PathVariable("compraId") Long compraId, @RequestBody @Valid NovoPagamentoPagseguroRequest novoPagamentoPagseguro) {
        Compra compra = compraRepository.findById(compraId).orElseThrow(NotFoundException::new);

        Pagamento pagamento = novoPagamentoPagseguro.toModel();
        Optional<PagamentoEfetuado> pagamentoEfetuado = compra.adicionarPagamento(pagamento);

        return executa(pagamentoEfetuado);
    }


    private ResponseEntity<?> executa(Optional<PagamentoEfetuado> possivelPagamentoEfetuado) {
        if (possivelPagamentoEfetuado.isEmpty()) return ResponseEntity.badRequest().body("Este item já foi pago!");
        acoesPosCompraService.executa(possivelPagamentoEfetuado.get());

        return ResponseEntity.ok().build();
    }
}
