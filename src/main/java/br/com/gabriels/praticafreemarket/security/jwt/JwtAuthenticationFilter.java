package br.com.gabriels.praticafreemarket.security.jwt;

import br.com.gabriels.praticafreemarket.security.service.FreeMarketUsuarioService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;
    private FreeMarketUsuarioService usuarioService;

    public JwtAuthenticationFilter(TokenManager tokenManager, FreeMarketUsuarioService usuarioService) {
        Assert.notNull(tokenManager, "Token manager não poder estar nulo!");
        Assert.notNull(usuarioService, "UsuarioService não poder estar nulo!");

        this.tokenManager = tokenManager;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String jwt = getTokenDaRequest(request);

        if (tokenManager.isValid(jwt)) {

            Long usuarioId = tokenManager.getUserIdFromToken(jwt);
            UserDetails usuarioDetails = usuarioService.loadUserById(usuarioId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(usuarioDetails, null, usuarioDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String getTokenDaRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        return null;
    }

}

