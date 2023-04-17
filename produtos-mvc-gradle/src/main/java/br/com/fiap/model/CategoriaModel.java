package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_CATEGORIA")
public class CategoriaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Long idCategoria;
	
	@Column(name = "NOME_CATEGORIA")
	private String nomeCategoria;
	
	@OneToMany(mappedBy = "categoria")
	private List<ProdutoModel> produtos;
	
	public CategoriaModel() {
		super();
	}

	public CategoriaModel(Long idCategoria, String nomeCategoria, List<ProdutoModel> produtos) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
		this.produtos = produtos;
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
