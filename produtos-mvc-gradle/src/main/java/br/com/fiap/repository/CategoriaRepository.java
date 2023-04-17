package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;
import jakarta.validation.Valid;

@Repository
public class CategoriaRepository {
	
	private static final String GET_ALL = "SELECT * FROM TB_CATEGORIA";
	private static final String GET = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
	private static final String SAVE = "INSERT INTO TB_CATEGORIA (NOME_CATEGORIA) VALUES (?)";
	private static final String UPDATE = "UPDATE TB_CATEGORIA SET NOME_CATEGORIA = ? WHERE ID_CATEGORIA =?";
	private static final String DELETE = "DELETE FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<CategoriaModel> findAll() {
		List<CategoriaModel> categorias = this.jdbcTemplate.query(GET_ALL, 
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class));
		
		return categorias;
		
	}
	
	public CategoriaModel findById(long id) {
		CategoriaModel categoria = this.jdbcTemplate.queryForObject(GET, 
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class), id);
		return categoria;
	}
	
	public void save(@Valid CategoriaModel categoria) {
		this.jdbcTemplate.update(SAVE, categoria.getNomeCategoria());
	}
	
	public void update(@Valid CategoriaModel categoria) {
		this.jdbcTemplate.update(UPDATE, categoria.getNomeCategoria(), categoria.getIdCategoria());
	}
	public void deleteById(long id) {
		this.jdbcTemplate.update(DELETE, id);
	}
	
	

}
