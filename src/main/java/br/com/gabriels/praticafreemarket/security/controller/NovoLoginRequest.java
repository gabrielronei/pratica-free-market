package br.com.gabriels.praticafreemarket.security.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class NovoLoginRequest {

    private final String login;
    private final String senha;

    public NovoLoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}

