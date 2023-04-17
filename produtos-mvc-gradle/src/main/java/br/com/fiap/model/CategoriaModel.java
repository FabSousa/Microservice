package br.com.fiap.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaModel {

	private long idCategoria;
	private String nomeCategoria;
	
	public CategoriaModel(long idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}
	public CategoriaModel() {
		super();
		
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
	@NotNull (message = "Nome é obrigatorio!")
	@Size(min = 2, max =50, message = "Nome deve ser entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
	
}
