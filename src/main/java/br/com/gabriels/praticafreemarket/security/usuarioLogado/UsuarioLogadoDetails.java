package br.com.gabriels.praticafreemarket.security.usuarioLogado;

import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class UsuarioLogadoDetails implements UserDetails {

    private Usuario usuario;
    private User springDetails;

    public UsuarioLogadoDetails(Usuario usuario) {
        this.usuario = usuario;
        this.springDetails = new User(usuario.getLogin(), usuario.getSenha(), Collections.emptyList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.springDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.springDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return this.springDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.springDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.springDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.springDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.springDetails.isEnabled();
    }

    public Usuario get() {
        return this.usuario;
    }

    public Long getId() {
        return this.usuario.getId();
    }
}
