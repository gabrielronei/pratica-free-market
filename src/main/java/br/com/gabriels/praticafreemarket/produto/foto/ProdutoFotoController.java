package br.com.gabriels.praticafreemarket.produto.foto;

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
import java.util.Set;

@RestController
public class ProdutoFotoController {

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoUploader fotoUploader;

    @CacheEvict(value = "detalhesProduto", allEntries = true)
    @PostMapping("/produto/{id}/adicionar-imagem")
    @Transactional
    public ResponseEntity adicionarFotos(@PathVariable("id") Long produtoId, @RequestBody @Valid NovaImagemRequest novaImagemRequest) {
        Usuario usuario = usuarioLogado.getUser().orElseThrow(NotFoundException::new);
        Produto produto = produtoRepository.findByIdAndFornecedor(produtoId, usuario).orElseThrow(NotFoundException::new);

        Set<Foto> fotos = novaImagemRequest.toModel(fotoUploader,
                new InformacoesBasicasParaUpload(usuario.getLogin(), produto.getNome()));

        produto.adicionarFotos(fotos);

        return ResponseEntity.ok().build();
    }
}
