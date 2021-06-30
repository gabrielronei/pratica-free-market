package br.com.gabriels.praticafreemarket.infra.email;

public class DadosEmail {

    private final String de;
    private final String para;
    private final String assunto;
    private final String corpo;

    private DadosEmail(EmailPassoFinal emailPassoFinal) {
        this.de = emailPassoFinal.de;
        this.para = emailPassoFinal.para;
        this.assunto = emailPassoFinal.assunto;
        this.corpo = emailPassoFinal.corpo;
    }

    public String getDe() {
        return de;
    }

    public String getPara() {
        return para;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public static DadosEmailInterface.EmailDeParaInterface deFreeMarketMail() {
        return new EmailDePara("freeMarket@email.com");
    }

    private static class EmailDePara implements DadosEmailInterface.EmailDeParaInterface {
        private final String de;

        public EmailDePara(String de) {
            this.de = de;
        }

        public DadosEmailInterface.EmailAssuntoInterface para(String para) {
            return new EmailAssunto(de, para);
        }
    }

    private static class EmailAssunto implements DadosEmailInterface.EmailAssuntoInterface {
        private final String de;
        private final String para;

        public EmailAssunto(String de, String para) {
            this.de = de;
            this.para = para;
        }

        public EmailCorpo comAssunto(String assunto) {
            return new EmailCorpo(this.de, this.para, assunto);
        }
    }

    private static class EmailCorpo implements DadosEmailInterface.EmailCorpoInterface {
        private final String de;
        private final String para;
        private final String assunto;

        public EmailCorpo(String de, String para, String assunto) {
            this.de = de;
            this.para = para;
            this.assunto = assunto;
        }

        public DadosEmailInterface.EmailPassoFinalInterface comCorpo(String corpo) {
            return new EmailPassoFinal(this.de, this.para, assunto, corpo);
        }
    }

    private static class EmailPassoFinal implements DadosEmailInterface.EmailPassoFinalInterface {
        private final String de;
        private final String para;
        private final String assunto;
        private final String corpo;

        public EmailPassoFinal(String de, String para, String assunto, String corpo) {
            this.de = de;
            this.para = para;
            this.assunto = assunto;
            this.corpo = corpo;
        }

        public DadosEmail build() {
            return new DadosEmail(this);
        }
    }
}
