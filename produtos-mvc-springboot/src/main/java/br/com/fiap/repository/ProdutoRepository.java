package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ProdutoModel;
import br.fiap.repository.mapping.ProdutoRowMapper;

@Repository
public class ProdutoRepository {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	private static final String GET_ALL = "SELECT * FROM TB_PRODUTO P INNER JOIN TB_CATEGORIA C"
										+ " ON P.ID_CATEGORIA = C.ID_CATEGORIA"
										+ " ORDER BY P.ID";
	
	private static final String GET_BY_ID = "SELECT * FROM TB_PRODUTO P INNER JOIN TB_CATEGORIA C"
											+ " ON P.ID_CATEGORIA = C.ID_CATEGORIA"
											+ " WHERE P.ID=?";
	
	private static final String SAVE = "INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) "
										+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE TB_PRODUTO SET"
										+ " NOME=?, SKU=?, DESCRICAO=?, CARACTERISTICAS=?, PRECO=?, ID_CATEGORIA=?"
										+ " WHERE ID=?";
	
	private static final String DELETE = "DELETE FROM TB_PRODUTO WHERE ID=?";


	public List<ProdutoModel> findAll() {
		return this.jdbcTemplate.query(GET_ALL, new ProdutoRowMapper());
	}

	public ProdutoModel findById(Long id) {
		return jdbcTemplate.queryForObject(GET_BY_ID, new ProdutoRowMapper(), id);
	}

	public void update(ProdutoModel produtoModel) {
		this.jdbcTemplate.update(UPDATE, 
				produtoModel.getNome(),
				produtoModel.getSku(),
				produtoModel.getDescricao(),
				produtoModel.getCaracteristicas(),
				produtoModel.getPreco(),
				produtoModel.getId(),
				produtoModel.getCategoria().getIdCategoria());
	}

	public void save(ProdutoModel produto) {
		this.jdbcTemplate.update(SAVE, 
				produto.getNome(),
				produto.getSku(),
				produto.getDescricao(),
				produto.getCaracteristicas(),
				produto.getPreco(),
				produto.getCategoria().getIdCategoria());
	}

	public void deleteById(long id) {
		this.jdbcTemplate.update(DELETE,id);
	}
}
