package br.com.gabriels.praticafreemarket.infra;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.util.Assert.hasText;

public final class BcryptPasswordEncoder {

    public static String encrypt(String senhaCrua) {
        hasText(senhaCrua, "A senha não pode estar em branco!");

        return new BCryptPasswordEncoder().encode(senhaCrua);
    }
}
