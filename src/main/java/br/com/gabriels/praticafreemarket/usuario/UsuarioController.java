package br.com.gabriels.praticafreemarket.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioReposiory usuarioReposiory;

    @PostMapping("/usuario")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoUsuarioRequest novoUsuario) {
        usuarioReposiory.save(novoUsuario.toModel());
        return ResponseEntity.ok().build();
    }
}
