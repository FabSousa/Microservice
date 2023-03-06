package br.com.fiap.model;

public class ProdutoModel {
	private Long id;
	private double preco;
	private String nome, sku, descricao, caracteristica;
	
	public ProdutoModel() {
		super();
	}
	
	public ProdutoModel(Long id, double preco, String nome, String sku, String descricao, String caracteristica) {
		super();
		this.id = id;
		this.preco = preco;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.caracteristica = caracteristica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

}
