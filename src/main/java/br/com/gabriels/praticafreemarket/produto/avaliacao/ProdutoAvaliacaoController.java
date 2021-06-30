package br.com.gabriels.praticafreemarket.produto.avaliacao;

import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import br.com.gabriels.praticafreemarket.produto.*;
import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogado;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProdutoAvaliacaoController {

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Autowired
    private ProdutoRepository produtoRepository;

    @CacheEvict(value = "detalhesProduto", allEntries = true)
    @PostMapping("/produto/{id}/avaliacao")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable("id") Long produtoId,
                                    @RequestBody @Valid NovaAvaliacaoRequest novaAvaliacaoRequest) {
        Usuario usuario = usuarioLogado.getUser().orElseThrow(NotFoundException::new);
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(NotFoundException::new);

        Avaliacao novaAvaliacao = novaAvaliacaoRequest.toModel(usuario);
        produto.adicionarAvaliacao(novaAvaliacao);

        return ResponseEntity.ok().build();
    }

}
