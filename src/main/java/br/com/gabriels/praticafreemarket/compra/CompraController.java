package br.com.gabriels.praticafreemarket.compra;

import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import br.com.gabriels.praticafreemarket.produto.*;
import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogado;
import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CompraController {

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EnviadorDeInteresseDeCompraService enviadorDeInteresseDeCompraService;

    @PostMapping("/compra")
    @Transactional
    public ResponseEntity comprar(@RequestBody @Valid NovaCompraRequest novaCompraRequest, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioLogado.getUser().orElseThrow(NotFoundException::new);

        Produto produto = produtoRepository.findById(novaCompraRequest.getProdutoId()).orElseThrow(NotFoundException::new);
        Optional<Compra> possivelCompra = produto.novaCompra(novaCompraRequest, usuario);

        if (possivelCompra.isEmpty()) return ResponseEntity.unprocessableEntity().body("Quantidade indisponível!");

        Compra compra = compraRepository.save(possivelCompra.get());
        enviadorDeInteresseDeCompraService.envia(compra);

        String urlCompra = compra.getURLCompra(uriComponentsBuilder);

        return ResponseEntity.ok(urlCompra);
    }

    @GetMapping("/compra/{id}")
    public ResponseEntity detalhar(@PathVariable("id") Long compraId) {
        Usuario usuario = usuarioLogado.getUser().orElseThrow(NotFoundException::new);
        Compra compra = compraRepository.findByIdAndComprador_Id(compraId, usuario.getId())
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(new CompraDetalhes(compra));
    }

}
