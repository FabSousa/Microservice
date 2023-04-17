package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.fiap.model.ProdutoModel;

@Component
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

}
