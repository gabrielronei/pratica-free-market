package br.com.gabriels.praticafreemarket.usuario;

import br.com.gabriels.praticafreemarket.infra.annotation.uniqueField.UniqueField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    @UniqueField(nomeCampo = "login", classeDominio = Usuario.class, message = "Já existe um usuario com este login")
    private final String login;

    @NotBlank
    @Length(min = 6)
    private final String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }
}
