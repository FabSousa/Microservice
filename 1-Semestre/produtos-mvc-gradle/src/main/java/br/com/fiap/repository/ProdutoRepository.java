package br.com.fiap.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.ProdutoModel;

@Component
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	@Query("select p from ProdutoModel p where p.preco = ?1 and p.categoria.nomeCategoria = ?2")
	List<ProdutoModel> findProdutosByCategory(BigDecimal preco, String nomeCategoria);

	@Transactional
	@Modifying
	@Query(value = "update tb_produto set nome = ?1, sku = ?2 where id = ?3", nativeQuery = true)
	void updateProjectNameAndSku(String nome, String sku, Long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete ProdutoModel where id = ?1")
	void deleteProdutoById(Long id);
}
