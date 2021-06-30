package br.com.gabriels.praticafreemarket.infra.email;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class FakeMailer implements EnviadorDeEmail {
    private static final Logger LOGGER = LoggerFactory.getLogger(FakeMailer.class);

    @Override
    public void envia(DadosEmail dadosEmail) {
        LOGGER.info("[Email] enviado com sucesso para -> {}", dadosEmail.getPara());
        System.out.println("----------------------------------------- EMAIL ENVIADO -----------------------------------------");
        System.out.println("De: " + dadosEmail.getDe());
        System.out.println("Para: " + dadosEmail.getPara());
        System.out.println("Assunto: " + dadosEmail.getAssunto());
        System.out.println("Corpo: " + dadosEmail.getCorpo());
    }
}
