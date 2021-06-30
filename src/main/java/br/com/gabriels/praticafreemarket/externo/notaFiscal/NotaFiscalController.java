package br.com.gabriels.praticafreemarket.externo.notaFiscal;

import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class NotaFiscalController {
    private Logger LOG = LoggerFactory.getLogger(NotaFiscalController.class);

    @PostMapping("/gerar/nota-fiscal")
    public ResponseEntity notaFiscal(@RequestBody @Valid NovaNotaFiscalRequest novaNotaFiscalRequest) {
        LOG.info("[NOTA FISCAL] -> Gerando nota fical para %s".formatted(novaNotaFiscalRequest.toString()));
        return ResponseEntity.ok().build();
    }
}
