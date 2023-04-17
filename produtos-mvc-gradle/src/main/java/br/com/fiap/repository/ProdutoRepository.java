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

	private static final String GET_ALL = "SELECT * FROM TB_PRODUTO P"
										+ " INNER JOIN TB_CATEGORIA C"
										+ " ON P.ID_CATEGORIA = C.ID_CATEGORIA"
										+ " INNER JOIN TB_MARCA M"
										+ " ON P.ID_MARCA = M.ID_MARCA"
										+ " ORDER BY ID";

	private static final String GET_BY_ID = "SELECT * FROM TB_PRODUTO P"
										+ " INNER JOIN TB_CATEGORIA C"
										+ " ON P.ID_CATEGORIA = C.ID_CATEGORIA"
										+ " INNER JOIN TB_MARCA M"
										+ " ON P.ID_MARCA = M.ID_MARCA"
										+ " WHERE P.ID=?";

	private static final String SAVE = "INSERT INTO TB_PRODUTO"
									+ " (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA, ID_MARCA) "
									+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE TB_PRODUTO SET"
										+ " NOME=?, SKU=?, DESCRICAO=?, CARACTERISTICAS=?, PRECO=?, ID_CATEGORIA=?, ID_MARCA=?"
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
				produtoModel.getCategoria().getIdCategoria(),
				produtoModel.getMarca().getIdMarca(),
				produtoModel.getId());
	}

	public void save(ProdutoModel produtoModel) {
		this.jdbcTemplate.update(SAVE, 
				produtoModel.getNome(),
				produtoModel.getSku(),
				produtoModel.getDescricao(),
				produtoModel.getCaracteristicas(),
				produtoModel.getPreco(),
				produtoModel.getCategoria().getIdCategoria(),
				produtoModel.getMarca().getIdMarca());
	}

	public void deleteById(Long id) {
		this.jdbcTemplate.update(DELETE, id);
	}
}
