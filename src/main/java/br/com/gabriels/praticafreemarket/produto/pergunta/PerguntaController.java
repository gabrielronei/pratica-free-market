package br.com.gabriels.praticafreemarket.produto.pergunta;

import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import br.com.gabriels.praticafreemarket.produto.*;
import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogado;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private EnviadorDePerguntaEmailService enviadorDePerguntaEmailService;

    @CacheEvict(value = "detalhesProduto", allEntries = true)
    @PostMapping("/produto/{id}/pergunta")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable("id") Long produtoId,
                                    @RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest,
                                    UriComponentsBuilder uriComponentsBuilder) {

        Usuario perguntador = usuarioLogado.getUser().orElseThrow(NotFoundException::new);
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(NotFoundException::new);
        Pergunta pergunta = perguntaRepository.save(novaPerguntaRequest.toModel(produto, perguntador));
        enviadorDePerguntaEmailService.envia(pergunta, uriComponentsBuilder);

        return ResponseEntity.ok().build();
    }

}
