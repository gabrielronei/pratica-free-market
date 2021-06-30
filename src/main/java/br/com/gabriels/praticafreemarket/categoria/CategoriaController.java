package br.com.gabriels.praticafreemarket.categoria;

import br.com.gabriels.praticafreemarket.infra.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria novaCategoria = novaCategoriaRequest
                .toModel(idCategoriaMae -> categoriaRepository.findById(idCategoriaMae).orElseThrow(NotFoundException::new));

        categoriaRepository.save(novaCategoria);
        return ResponseEntity.ok().build();
    }
}
