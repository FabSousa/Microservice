package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;

@Repository
public class CategoriaRepository {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	private static final String GET_ALL = "SELECT * FROM TB_CATEGORIA ORDER BY ID_CATEGORIA";
	private static final String GET_BY_ID = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA=?";
	private static final String SAVE = "INSERT INTO TB_CATEGORIA (NOME_CATEGORIA) VALUES (?)";
	private static final String UPDATE = "UPDATE TB_CATEGORIA SET NOME_CATEGORIA=? WHERE ID_CATEGORIA=?";
	private static final String DELETE = "DELETE FROM TB_CATEGORIA WHERE ID_CATEGORIA=?";
	
	public List<CategoriaModel> findAll() {
		return this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class));
	}
	
	public CategoriaModel findById(Long id) {
		 return jdbcTemplate.queryForObject(GET_BY_ID, new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class), id);
	}

	public void save(CategoriaModel categoria) {
		this.jdbcTemplate.update(SAVE, 
				categoria.getNomeCategoria());
	}
	
	public void update(CategoriaModel categoriaModel) {
		this.jdbcTemplate.update(UPDATE, 
				categoriaModel.getNomeCategoria(),
				categoriaModel.getIdCategoria());
	}
	
	public void deleteById(Long id) {
		this.jdbcTemplate.update(DELETE, id);
	}
}
