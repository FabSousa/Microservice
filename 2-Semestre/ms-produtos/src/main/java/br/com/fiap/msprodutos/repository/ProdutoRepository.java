package br.com.fiap.msprodutos.repository;

import br.com.fiap.msprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
