package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.categoria.*;
import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import br.com.gabriels.praticafreemarket.produto.foto.*;
import br.com.gabriels.praticafreemarket.produto.pergunta.*;
import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogado;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Autowired
    private FotoUploader fotoUploader;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @PostMapping("/produto")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {
        Usuario usuario = usuarioLogado.getUser().orElseThrow(NotFoundException::new);
        Function<Long, Categoria> findCategoryById = (categoriaId) -> categoriaRepository.findById(categoriaId)
                .orElseThrow(NotFoundException::new);

        Produto produto = novoProdutoRequest.toModel(usuario, fotoUploader, findCategoryById);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @Cacheable(value = "detalhesProduto")
    @GetMapping("/produto/{id}")
    public ResponseEntity detalharProduto(@PathVariable("id") Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(NotFoundException::new);
        List<Pergunta> perguntas = perguntaRepository.findAllByProduto_IdOrderByDataEHoraCriacaoDesc(produtoId);

        return ResponseEntity.ok(new ProdutoDetalhes(produto, perguntas));
    }
}
