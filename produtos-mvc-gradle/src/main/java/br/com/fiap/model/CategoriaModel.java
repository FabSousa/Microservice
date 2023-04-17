package br.com.fiap.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaModel {
	
	private Long idCategoria;
	private String nomeCategoria;
	
	public CategoriaModel() {
		super();
	}
	
	public CategoriaModel(Long idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long id) {
		this.idCategoria = id;
	}

	@NotNull(message = "Nome é obrigatório")
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nome) {
		this.nomeCategoria = nome;
	}
	
	
}
