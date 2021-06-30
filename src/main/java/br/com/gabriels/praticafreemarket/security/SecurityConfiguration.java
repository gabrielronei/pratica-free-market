package br.com.gabriels.praticafreemarket.security;

import br.com.gabriels.praticafreemarket.security.jwt.*;
import br.com.gabriels.praticafreemarket.security.service.FreeMarketUsuarioService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final FreeMarketUsuarioService freeMarketUsuarioService;
    private final TokenManager tokenManager;

    public SecurityConfiguration(FreeMarketUsuarioService freeMarketUsuarioService, TokenManager tokenManager) {
        this.freeMarketUsuarioService = freeMarketUsuarioService;
        this.tokenManager = tokenManager;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.freeMarketUsuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(POST, "/api/auth").permitAll()
                .antMatchers(POST,"/usuario").permitAll()
                .antMatchers(POST,"/cadastrar-ranking").permitAll()
                .antMatchers(POST,"/gerar/nota-fiscal").permitAll()
                .antMatchers(GET, "/produto/{id:[0-9]+}").permitAll()
                .antMatchers("/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenManager, freeMarketUsuarioService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint());
    }
}
