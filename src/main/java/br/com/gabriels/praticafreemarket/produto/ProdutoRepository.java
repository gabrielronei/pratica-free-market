package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.usuario.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByIdAndFornecedor(Long id, Usuario fornecedor);
}
