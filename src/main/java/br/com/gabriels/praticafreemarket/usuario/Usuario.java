package br.com.gabriels.praticafreemarket.usuario;

import br.com.gabriels.praticafreemarket.infra.BcryptPasswordEncoder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static org.springframework.util.Assert.hasText;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true)
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    @NotNull
    @FutureOrPresent
    private LocalDate dataCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, String senhaCrua) {
        hasText(login, "O login é obrigatorio!");
        hasText(senhaCrua, "A senha é obrigatoria!");

        this.login = login;
        this.senha = BcryptPasswordEncoder.encrypt(senhaCrua);
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }
}
