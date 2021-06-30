package br.com.gabriels.praticafreemarket.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioReposiory extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
