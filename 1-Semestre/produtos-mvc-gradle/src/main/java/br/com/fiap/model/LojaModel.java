package br.com.fiap.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "TB_LOJA")
public class LojaModel {

	@Id
	@Column(name = "ID_LOJA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLoja;
	
	@Column(name = "NOME_LOJA")
	private String nomeLoja;
	
	@ManyToMany(mappedBy = "lojas")
	private List<ProdutoModel> produtos;

	public LojaModel() {
		super();
	}
	
	public LojaModel(long idLoja, String nomeLoja, List<ProdutoModel> produtos) {
		super();
		this.idLoja = idLoja;
		this.nomeLoja = nomeLoja;
		this.produtos = produtos;
	}

	public long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(long idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}
	
}
