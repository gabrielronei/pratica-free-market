package br.com.gabriels.praticafreemarket.security.usuarioLogado;

import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class UsuarioLogado {

    public Optional<Usuario> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return Optional.empty();

        Object principal = authentication.getPrincipal();
        if (principal instanceof UsuarioLogadoDetails usuarioLogado) {
            return Optional.of(usuarioLogado.get());
        }

        return Optional.empty();
    }
}