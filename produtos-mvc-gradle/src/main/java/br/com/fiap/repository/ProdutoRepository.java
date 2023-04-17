package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.map.ProdutoRowMapper;

@Repository
public class ProdutoRepository {

	
	private static final String GET_ALL = "SELECT * FROM TB_PRODUTO P INNER JOIN TB_CATEGORIA C ON P.ID_CATEGORIA = C.ID_CATEGORIA INNER JOIN TB_MARCA M ON P.ID_MARCA = M.ID_MARCA order by p.id";
	private static final String SAVE = "INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, PRECO, CARACTERISTICAS, ID_CATEGORIA, ID_MARCA) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET = "SELECT * FROM TB_PRODUTO P INNER JOIN TB_CATEGORIA C ON P.ID_CATEGORIA = C.ID_CATEGORIA INNER JOIN TB_MARCA M ON P.ID_MARCA=M.ID_MARCA WHERE ID=?";
	private static final String UPDATE = "UPDATE TB_PRODUTO SET NOME=?, SKU=?, DESCRICAO=?, CARACTERISTICAS=?, PRECO=?, ID_CATEGORIA=?, ID_MARCA=? WHERE ID=?";
	private static final String DELETE = "DELETE FROM TB_PRODUTO WHERE ID=?";
	
	
    @Autowired
	public JdbcTemplate jdbcTemplate;
    
	
	public ProdutoRepository() {
       super();
	}
	

	public List<ProdutoModel> findAll() {
		List<ProdutoModel> produtos = this.jdbcTemplate.query(GET_ALL, 
				new ProdutoRowMapper());
		return produtos;
	}
	

	public ProdutoModel findById(long id) {
		ProdutoModel produto = this.jdbcTemplate.queryForObject(GET, 
				new ProdutoRowMapper(), id);
		return produto;
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

	public void save(ProdutoModel produto) {
		this.jdbcTemplate.update(SAVE, 
				produto.getNome(),
				produto.getSku(),
				produto.getDescricao(),
				produto.getPreco(),
				produto.getCaracteristicas(),
				produto.getCategoria().getIdCategoria(),
				produto.getMarca().getIdMarca());
	}
	
	public void deleteById(long id) {
		this.jdbcTemplate.update(DELETE,id);
	}
}
