package br.com.gabriels.praticafreemarket.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findByIdAndComprador_Id(Long compraId, Long compradorId);
}
