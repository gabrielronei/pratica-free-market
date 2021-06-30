package br.com.gabriels.praticafreemarket.pagamento;

import io.jsonwebtoken.lang.Assert;

import java.util.Arrays;

enum PagamentoStatus {
    SUCESSO(1), ERRO(0);

    private int paypalStatusCode;

    PagamentoStatus(int paypalStatusCode) {
        this.paypalStatusCode = paypalStatusCode;
    }

    public static boolean estaPago(PagamentoStatus pagamentoStatus) {
        return SUCESSO.equals(pagamentoStatus);
    }

    public static PagamentoStatus getStatusBy(int paypalStatusCode) {
        Assert.state((paypalStatusCode >= 0 && paypalStatusCode <= 1), "ERRO = 0 ou SUCESSO = 1");

        return Arrays.stream(PagamentoStatus.values())
                .filter(status -> paypalStatusCode == status.paypalStatusCode)
                .findFirst().orElse(ERRO);
    }
}
