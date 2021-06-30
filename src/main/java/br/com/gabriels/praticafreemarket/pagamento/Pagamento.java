package br.com.gabriels.praticafreemarket.pagamento;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static org.springframework.util.Assert.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String gatewayPagamentoId;

    @NotNull
    @Enumerated(STRING)
    private PagamentoStatus status;

    @NotNull
    private LocalDateTime dataRetornoGateway;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(String gatewayPagamentoId, PagamentoStatus status) {
        hasText(gatewayPagamentoId, "O gatewayPagamentoId é obrigatorio!");
        notNull(status, "O status é obrigatorio!");

        this.gatewayPagamentoId = gatewayPagamentoId;
        this.status = status;
        this.dataRetornoGateway = LocalDateTime.now();
    }

    public boolean estaPago() {
        return PagamentoStatus.estaPago(this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(gatewayPagamentoId, pagamento.gatewayPagamentoId) && status == pagamento.status;
    }

    @Override
    public int hashCode() {
        int result = gatewayPagamentoId.hashCode();
        result = 31 * result + status.hashCode();

        return result;
    }
}
