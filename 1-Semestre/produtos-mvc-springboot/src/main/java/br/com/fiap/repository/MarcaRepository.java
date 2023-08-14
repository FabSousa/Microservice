package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.MarcaModel;

@Repository
public class MarcaRepository {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	private static final String GET_ALL = "SELECT * FROM TB_MARCA ORDER BY ID_MARCA";
	private static final String GET_BY_ID = "SELECT * FROM TB_MARCA WHERE ID_MARCA=?";
	private static final String SAVE = "INSERT INTO TB_MARCA (NOME_MARCA) VALUES (?)";
	private static final String UPDATE = "UPDATE TB_MARCA SET NOME_MARCA=? WHERE ID_MARCA=?";
	private static final String DELETE = "DELETE FROM TB_MARCA WHERE ID_MARCA=?";
	
	public List<MarcaModel> findAll() {
		return this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class));
	}
	
	public MarcaModel findById(Long id) {
		 return jdbcTemplate.queryForObject(GET_BY_ID, new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class), id);
	}

	public void save(MarcaModel marca) {
		this.jdbcTemplate.update(SAVE, 
				marca.getNomeMarca());
	}
	
	public void update(MarcaModel marcaModel) {
		this.jdbcTemplate.update(UPDATE, 
				marcaModel.getNomeMarca(),
				marcaModel.getIdMarca());
	}
	
	public void deleteById(Long id) {
		this.jdbcTemplate.update(DELETE, id);
	}
}
