package br.com.gabriels.praticafreemarket.security.service;

import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogadoDetails;
import br.com.gabriels.praticafreemarket.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class FreeMarketUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioReposiory usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Não foi possível encontrar usuário com email: " + login));
        return new UsuarioLogadoDetails(usuario);
    }

    public UserDetails loadUserById(Long usuarioId) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsernameNotFoundException("Não foi possível encontrar o usuário com id: " + usuarioId));
        return new UsuarioLogadoDetails(usuario);
    }
}
