package br.com.gabriels.praticafreemarket.security.controller;

import br.com.gabriels.praticafreemarket.security.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/api/auth")
    public ResponseEntity<AuthenticationTokenOutputDto> autenticar(@RequestBody NovoLoginRequest novoLogin) {

        UsernamePasswordAuthenticationToken authenticationToken = novoLogin.toModel();

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            String jwt = tokenManager.gerarToken(authentication);

            AuthenticationTokenOutputDto tokenResponse = new AuthenticationTokenOutputDto("Bearer", jwt);
            return ResponseEntity.ok(tokenResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
