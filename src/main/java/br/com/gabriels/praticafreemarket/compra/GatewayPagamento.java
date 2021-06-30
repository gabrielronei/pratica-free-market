package br.com.gabriels.praticafreemarket.compra;

import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.util.Assert.notNull;

enum GatewayPagamento {

    PAYPAL {
        @Override
        String getURL(Compra compra, UriComponentsBuilder urlRetorno) {
            notNull(compra, "A compra não pode estar nula!");
            notNull(urlRetorno, "A urlRetorno não pode estar nula!");

            return "https://paypal.com/%s?redirectUrl=%s".formatted(compra.getId(), getURLRetorno(compra, urlRetorno));
        }
    }, PAGSEGURO {
        @Override
        String getURL(Compra compra, UriComponentsBuilder urlRetorno) {
            notNull(compra, "A compra não pode estar nula!");
            notNull(urlRetorno, "A urlRetorno não pode estar nula!");

            return "https://pagseguro.com?returnId=%s&redirectUrl=%s".formatted(compra.getId(), getURLRetorno(compra, urlRetorno));
        }
    };

    abstract String getURL(Compra compra, UriComponentsBuilder urlRetorno);

    String getURLRetorno(Compra compra, UriComponentsBuilder urlRetorno) {
        return urlRetorno.path("/pagamento-{gatewayPagamento}/{compraId}").build(this.toString().toLowerCase(), compra.getId()).toString();
    }
}
