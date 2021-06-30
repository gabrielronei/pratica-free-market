package br.com.gabriels.praticafreemarket.externo.ranking;

import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RankingController {
    private Logger LOG = LoggerFactory.getLogger(RankingController.class);

    @PostMapping("/cadastrar-ranking")
    public ResponseEntity ranking(@RequestBody @Valid NovoRankingRequest novoRankinRequest) {
        LOG.info("[RANKING] -> Cadastrando fornecedor no ranking %s".formatted(novoRankinRequest.toString()));
        return ResponseEntity.ok().build();
    }
}
