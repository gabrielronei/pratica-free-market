package br.com.gabriels.praticafreemarket.infra.email;

interface DadosEmailInterface {

    interface EmailDeParaInterface {
        EmailAssuntoInterface para(String de);
    }

    interface EmailAssuntoInterface {
        EmailCorpoInterface comAssunto(String assunto);
    }

    interface EmailCorpoInterface {
        EmailPassoFinalInterface comCorpo(String corpo);
    }

    interface EmailPassoFinalInterface {
        DadosEmail build();
    }
}
