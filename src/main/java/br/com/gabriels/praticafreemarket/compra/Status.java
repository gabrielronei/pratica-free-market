package br.com.gabriels.praticafreemarket.compra;

enum Status {
    INICIADO, FINALIZADO;

    public Status finaliza() {
        return FINALIZADO;
    }
}