package br.fiap.repository.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;

public class ProdutoRowMapper implements RowMapper<ProdutoModel> {

	@Override
	public ProdutoModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		ProdutoModel produtoModel = new BeanPropertyRowMapper<>(ProdutoModel.class).mapRow(rs, rowNum);
		CategoriaModel categoriaoModel = new BeanPropertyRowMapper<>(CategoriaModel.class).mapRow(rs, rowNum);
		
		produtoModel.setCategoria(categoriaoModel);
		
		return produtoModel;
	}

}
